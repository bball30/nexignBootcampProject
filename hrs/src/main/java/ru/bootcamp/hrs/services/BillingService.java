package ru.bootcamp.hrs.services;

import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.bootcamp.hrs.dto.BillingResponse;
import ru.bootcamp.hrs.dto.CallDetails;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Log4j2
public class BillingService {

    public List<BillingResponse> calculateCost(Resource resource) throws IOException {
        InputStream inputStream = resource.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String[] cdrPlus = br.lines().parallel().collect(Collectors.joining("\n")).split("\n");

        Map<String, List<CallDetails>> abonentCallsMap = getAbonentCallsListFromCdr(cdrPlus);

        List<BillingResponse> responses = new ArrayList<>();
        for (Map.Entry<String, List<CallDetails>> abonent : abonentCallsMap.entrySet()) {
            for (CallDetails callDetails : abonent.getValue()) {
                double cost = 0;
                long durationInMinutes = 0;
                long durationInSeconds = Duration.between(callDetails.getStartTime(), callDetails.getEndTime()).toSeconds();
                if (durationInSeconds % 60 == 0) {
                    durationInMinutes = durationInSeconds / 60;
                } else {
                    durationInMinutes = durationInSeconds / 60 + 1;
                }

                if ((!callDetails.getIncomingPaid() && callDetails.getCallType().equals("02"))
                        || (!callDetails.getOutgoingPaid() && callDetails.getCallType().equals("01"))) {
                    responses.add(new BillingResponse(abonent.getKey(), callDetails.getCallType(),
                            callDetails.getTariffIndex(), callDetails.getStartTime(),
                            callDetails.getEndTime(), durationInSeconds, cost)
                    );
                    continue;
                }

                while (durationInMinutes != 0) {
                    durationInMinutes--;
                    if (callDetails.getFixedIncludedMinutes() != 0) {
                        callDetails.setFixedIncludedMinutes(callDetails.getFixedIncludedMinutes() - 1);
                        cost += callDetails.getIncludedPriceForMinute();
                    } else {
                        cost += callDetails.getPriceForMinute();
                    }
                }
                responses.add(new BillingResponse(abonent.getKey(), callDetails.getCallType(),
                        callDetails.getTariffIndex(), callDetails.getStartTime(),
                        callDetails.getEndTime(), durationInSeconds, cost)
                );

                abonent.getValue().forEach(cd ->
                        cd.setFixedIncludedMinutes(callDetails.getFixedIncludedMinutes())
                );
            }
        }
        return responses;
    }

    private Map<String, List<CallDetails>> getAbonentCallsListFromCdr(String[] cdrPlus) {
        Map<String, List<CallDetails>> abonentCallsMap = new HashMap<>();

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        Arrays.stream(cdrPlus).forEach(line -> {
            log.log(Level.INFO, line);
            String[] arguments = line.split(",");
            String callType = arguments[0];
            String numberPhone = arguments[1];
            LocalDateTime startTime = LocalDateTime.parse(arguments[2].trim(), dateFormat);
            LocalDateTime endTime = LocalDateTime.parse(arguments[3].trim(), dateFormat);
            String tariffIndex = arguments[4];
            Long fixedPrice = Long.parseLong(arguments[5]);
            Long fixedIncludedMinutes = Long.parseLong(arguments[6]);
            Float includedPriceForMinute = Float.parseFloat(arguments[7]);
            Float priceForMinute = Float.parseFloat(arguments[8]);
            boolean incomingPaid = Boolean.parseBoolean(arguments[9]);
            boolean outgoingPaid = Boolean.parseBoolean(arguments[10]);

            if (!abonentCallsMap.containsKey(numberPhone)) {
                abonentCallsMap.put(numberPhone, new ArrayList<>());
            }

            abonentCallsMap.get(numberPhone).add(new CallDetails(
                    callType, startTime, endTime, tariffIndex, fixedPrice, fixedIncludedMinutes,
                    includedPriceForMinute, priceForMinute, incomingPaid, outgoingPaid
            ));
        });
        return abonentCallsMap;
    }
}
