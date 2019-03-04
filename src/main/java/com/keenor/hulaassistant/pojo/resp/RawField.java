package com.keenor.hulaassistant.pojo.resp;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class RawField {
    /**
     * _id : 59cdecfd6f1d65ba08316189
     * __v : 0
     * _organization : 59cb5c718e1e92a702eca340
     * _venue : 59cc969742fa6b6703843bbe
     * closeDate : 2018-06-10T16:00:00.000Z
     * closeDate_open : null
     * closeOpen_endDate : 2019-02-07T15:59:59.000Z
     * closeOpen_startDate : 2019-02-03T16:00:00.000Z
     * createDate : 2017-09-29T06:49:33.031Z
     * name : 羽毛球01
     * lights : ["5aaf1231272068213fc3795a"]
     * isDelete : false
     * isOpen : false
     * isUsed : true
     * items : ["59cc95c342fa6b6703843bb9"]
     */

    private String _id;
    private int __v;
    private String _organization;
    private String _venue;
    private String closeDate;
    private Object closeDate_open;
    private String closeOpen_endDate;
    private String closeOpen_startDate;
    private String createDate;
    private String name;
    private boolean isDelete;
    private boolean isOpen;
    private boolean isUsed;
    private List<String> lights;
    private List<String> items;
}
