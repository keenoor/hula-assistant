package com.keenor.hulaassistant.service;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Streams;
import com.google.gson.reflect.TypeToken;
import com.keenoor.toolkit.utils.httpclient.HttpClientUtil;
import com.keenoor.toolkit.utils.httpclient.HttpCodeException;
import com.keenor.hulaassistant.config.BizException;
import com.keenor.hulaassistant.config.ResponseCode;
import com.keenor.hulaassistant.constants.Consts;
import com.keenor.hulaassistant.dao.MemberDao;
import com.keenor.hulaassistant.dao.VoteInfoDao;
import com.keenor.hulaassistant.entity.MemberPO;
import com.keenor.hulaassistant.entity.VotePO;
import com.keenor.hulaassistant.pojo.CountBo;
import com.keenor.hulaassistant.pojo.base.OldResult;
import com.keenor.hulaassistant.pojo.resp.MyOrderResp;
import com.keenor.hulaassistant.pojo.vo.MobileVo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

import static com.keenor.hulaassistant.constants.HulaUrls.API_ORDERLISTS;
import static com.keenor.hulaassistant.constants.HulaUrls.HOST;

@Slf4j
@Service
public class VoteService {

    @Resource
    VoteInfoDao voteInfoDao;
    @Resource
    MemberDao memberDao;

    public int statistic() {
        List<VotePO> votePOList = voteInfoDao.findValid();
        VotePO votePO = voteInfoDao.fixValid();
        VotePO votePO2 = voteInfoDao.fixValid2();
        votePOList.add(votePO);
        votePOList.add(votePO2);

        Map<Integer, AtomicInteger> voteMap = Maps.newTreeMap(Integer::compareTo);

        for (VotePO po : votePOList) {
            String voteInfo = po.getVoteInfo();
            String replace = voteInfo.replace("[", "").replace("]", "");
            String[] array = replace.split(",");
            Set<Integer> set = Arrays.stream(array).map(Integer::parseInt).collect(Collectors.toSet());
            for (Integer s : set) {
                if (voteMap.get(s) == null) {
                    voteMap.put(s, new AtomicInteger(1));
                } else {
                    voteMap.get(s).getAndIncrement();
                }
            }
        }

        Map<Integer, String> memberMap = memberDao.findMember().stream().collect(Collectors.toMap(i -> i.getId().intValue(), MemberPO::getName));
        outputCsv(voteMap, memberMap);

//        outputMobile(votePOList);

        log.info(voteMap.toString() + " -----------");
        return votePOList.size();
    }

    private void outputMobile(List<VotePO> votePOList) {
        File csv = new File("mobile.csv");
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csv, true), Charset.forName("gbk")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 50; i++) {
            HashMap<String, Object> map = Maps.newHashMap();
            map.put("key", "ad1d534ada94441bab34bf23318fcef8");
            map.put("mobiles", votePOList.get(i).getMobile());

            MobileVo result;
            try {
                result = HttpClientUtil
                        .<MobileVo>get("http://apis.haoservice.com/thirdnode/phonenotest/")
                        .params(map).clazz(MobileVo.class)
                        .execute();
            } catch (HttpCodeException e) {
                log.error("--> {}", e.getStatusLine());
                throw new BizException(ResponseCode.ERROR, e);
            }
            if (result.getError_code() == 0) {
                if (!result.getResult().getStatus().equals("1")) {
                    try {
                        bw.write(result.getResult().getMobile() + "," + result.getResult().getStatus());
                        bw.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void outputCsv(Map<Integer, AtomicInteger> voteMap, Map<Integer, String> memberMap) {
        File csv = new File("vote.csv");
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(csv, true), Charset.forName("gbk")));

            bw.write("编号" + "," + "称呼" + "," + "票数");
            bw.newLine();

            for (Map.Entry<Integer, AtomicInteger> entry : voteMap.entrySet()) {
                bw.write(entry.getKey() + "," + memberMap.get(entry.getKey()) + "," + entry.getValue().toString());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
