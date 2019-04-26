// import excel
// var script = document.createElement('script');
// script.src = "https://unpkg.com/xlsx/dist/xlsx.full.min.js";
// document.getElementsByTagName('head')[0].appendChild(script);

// 调试用
var bufflmrs = [];
var myarraystr = '[';

//

function addHtml() {
    var ele = document.createElement('p');
    ele.id = 'hack';
    ele.innerHTML = '<div style="background: rosybrown; padding: 10px;">\n' +
        '    <input type="file" id="files" style="display:none" onchange="readExcel()"/>\n' +
        '    <input type="button" id="import" value="1.导入" />\n' +
        '    <input type="button" id="check" value="2.检查身份证号" onclick="check()"/>\n' +
        '    <input type="button" id="handleAddr" value="3.地址切分编号" onclick="handleAddr()"/>\n' +
        '    <input type="button" id="iptData" value="4.填写数据" onclick="iptData()"/>\n' +
        '    <ol>\n' +
        '        <li>操作步骤：</li>\n' +
        '        <li>导入excel</li>\n' +
        '        <li>检查身份证姓名的匹配，注意观察日志</li>\n' +
        '        <li>地址切分编号，注意观察日志</li>\n' +
        '        <li>若检查不通过，请修改 excel 数据再试</li>\n' +
        '    </ol>\n' +
        '</div>';
    var head2 = document.getElementsByClassName('head')[0];
    var ref = document.getElementById('logoId');
    head2.insertBefore(ele, ref);
}

//////////// 调试
// addHtml();
///////////

$("#import").click(function () {
    console.log('import click');
    $("#files").click();
});

var json = [];

function readExcel() {
    console.log('import excel start');
    var selectedFile = document.getElementById("files").files[0];//获取读取的File对象
    var name = selectedFile.name;
    var size = selectedFile.size;
    console.log("文件名: " + name + "大小：" + size);

    var reader = new FileReader();
    reader.readAsBinaryString(selectedFile);
    reader.onload = function () {
        var workbook = XLSX.read(this.result, {type: 'binary'});
        // console.log(workbook);
        var sheetNames = workbook.SheetNames;
        var worksheet = workbook.Sheets[sheetNames[0]];
        json = XLSX.utils.sheet_to_json(worksheet);
        console.log(json);
    };
}

function check() {
    for (var i = 0; i < json.length; i++) {
        var name = json[i].name.trim();
        var card = json[i].card.trim();
        var address = json[i].address.trim();

        $.ajax({
            type: "post",
            url: "/zfp/com/loginoutservice/checkmessage",
            data: {
                card: card,
                zsxm: name
            },
            async: false,
            success: function (data) {
                if (data === 'OK') {
                    console.log(i + 1 + ' 检验成功-> ' + name);
                } else {
                    console.error(i + 1 + ' 检验失败-> ' + name);
                }
            }
        });
    }
}

function iptData() {
    if (json.length !== addrCodes.length) {
        alert('地址拆分与身份 数量不匹配, json: ' + json.length + 'addrs: ' + addrCodes.length);
        return;
    }
    for (var i = 0; i < json.length; i++) {
        var tmp = [];
        var name = json[i].name.trim();
        var card = json[i].card.trim();
        var addr = json[i].address.trim();
        tmp.push(name);
        tmp.push(card);
        tmp = tmp.concat(addrCodes[i]);
        tmp.push('true');
        bufflmrs.push(tmp);

        var addrs;
        if (rgx.test(addr)) {
            addrs = rgx.exec(addr);
        } else if (rgx2.test(addr)) {
            addrs = rgx2.exec(addr);
        }

        var strJson = {
            "xm": name,
            "sfzh": card,
            "dsf": addrCodes[i][0],
            "dsf1": addrs[1],
            "dss1": addrs[2],
            "dst1": addrs[addrs.length - 1]==="0" ? "" : addrs[3],
            "dss": addrCodes[i][1],
            "dst": addrCodes[i][2],
            "di": addrs[addrs.length - 1]
        };
        myarraystr += JSON.stringify(strJson);

        if (i === json.length-1){
            myarraystr += ']';
        }
    }
    alert('数据导入成功，总数量为：' + json.length);
}

var rgx = /^([\u4e00-\u9fa5]+[省|自治区])([\u4e00-\u9fa5]+市)([\u4e00-\u9fa5]+[区市县])(.+)$/;
var rgx2 = /^([\u4e00-\u9fa5]{2}市)([\u4e00-\u9fa5]+[区|县])(.+)$/;

var addrCodes = [];

function handleAddr() {
    // var addr1 = '浙江省杭州市西湖区文三西路100号';
    // var addr2 = '湖北省孝感市安陆市小河乡';
    // var addr5 = '西藏自治区拉萨市城关区西田街';
    // var addr6 = '山东省济南市某某县摩托村';
    // var addr3 = '北京市海淀区人大附中';
    // var addr4 = '重庆市市奉节县船夫村';

    for (var i = 0; i < json.length; i++) {
        var addr = json[i].address.trim();
        if (rgx.test(addr)) {
            var array = rgx.exec(addr);
            if (array.length !== 5) {
                console.error('rgx 未知错误，地址为：' + addr);
                return;
            }
            var addrCode = getAddrCode(array);
            addrCodes.push(addrCode);
            continue;
        }
        if (rgx2.test(addr)) {
            var array2 = rgx2.exec(addr);
            if (array2.length !== 4) {
                console.error('rgx2 未知错误，地址为：' + addr);
                return;
            }
            var addrCode2 = getAddrCode(array2);
            addrCodes.push(addrCode2);
            continue;
        }
        console.warn(i + 1 + '地址不合规-> ' + addr);
    }
    alert('地址检查完成')
}

function getAddrCode(addrs) {
    var codes = [];
    // 找省
    var provArry = dataItems['0_0'];
    for (var i = 0; i < provArry.length; i++) {
        if (provArry[i].indexOf(addrs[1]) !== -1) {
            codes.push(provArry[i].split('|')[1]);

            // 找省会 or 直辖市区
            var cityKey = '0_' + i + '_0';
            var cityArry = dataItems[cityKey];
            for (var j = 0; j < cityArry.length; j++) {
                if (cityArry[j].indexOf(addrs[2]) !== -1) {
                    codes.push(cityArry[j].split('|')[1]);

                    // 找城区 or 完毕
                    var countyKey = '0_' + i + '_' + j + '_0';
                    var countyArry = dataItems[countyKey];
                    // 直辖市查找结束
                    if (typeof (countyArry) == "undefined") {
                        codes.push('0');
                        codes.push(addrs[3]);
                        return codes;
                    }

                    if (addrs.length !== 5) {
                        console.error('地址切分 错误：' + addrs);
                        return;
                    }

                    // 继续找城区
                    for (var k = 0; k < countyArry.length; k++) {
                        if (countyArry[k].indexOf(addrs[3]) !== -1) {
                            codes.push(countyArry[k].split('|')[1]);
                            codes.push(addrs[4]);
                            return codes;
                        }
                        if (k === countyArry.length - 1) {
                            console.error('区县级 未找到错误：' + addrs[3]);
                            return;
                        }
                    }
                }

                if (j === cityArry.length - 1) {
                    codes.push('0');
                    codes.push(addrs[3] + addrs[4]);
                    return codes;
                }
            }

        }
        if (i === provArry.length - 1) {
            console.error('省级 错误：' + addrs[1]);
            return;
        }
    }
}
