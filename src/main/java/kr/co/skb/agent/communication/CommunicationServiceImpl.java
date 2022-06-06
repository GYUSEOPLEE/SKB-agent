package kr.co.skb.agent.communication;

import kr.co.skb.agent.domain.Kickboard;
import kr.co.skb.agent.domain.KickboardLocation;
import kr.co.skb.agent.domain.KickboardUse;
import kr.co.skb.agent.util.CommunicationUtil;
import kr.co.skb.agent.util.KickboardUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Properties;

@Log4j2
@Service
public class CommunicationServiceImpl implements CommunicationService {
    @Autowired private KickboardUtil kickboardUtil;
    @Autowired private CommunicationUtil communicationUtil;
    @Autowired private KickboardUse kickboardUse;

    @Override
    public void sendKickboard() {
        final Kickboard kickboard = kickboardUtil.getKickboard();

        try {
            communicationUtil.request(kickboard);
        } catch (Exception e) {
            log.error("킥보드 정보 송신 실패");
            log.error(e.getMessage());
        }
    }

    @Override
    public void sendKickboardUse(String use) {
        kickboardUse.setUse(use);

        try {
            communicationUtil.request(kickboardUse);
        } catch (Exception e) {
            log.error("킥보드 사용 정보 송신 실패");
            log.error(e.getMessage());
        }
    }

    @Override
    public void sendKickboardLocation(KickboardLocation kickboardLocation) {
        try {
            communicationUtil.request(kickboardLocation);
        } catch (Exception e) {
            log.error("킥보드 위치 정보 송신 실패");
            log.error(e.getMessage());
        }
    }
}
