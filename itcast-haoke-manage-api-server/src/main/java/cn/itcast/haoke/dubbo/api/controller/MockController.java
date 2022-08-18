package cn.itcast.haoke.dubbo.api.controller;

import cn.itcast.haoke.dubbo.api.config.MockConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mock")
@CrossOrigin
public class MockController {
    @Autowired
    private MockConfig mockConfig;

    @GetMapping("indexMenu")
    public String indexMenu() {
        return this.mockConfig.getIndexMenu();
    }

    @GetMapping("indexInfo")
    public String indexInfo() {
        return this.mockConfig.getIndexInfo();
    }

    @GetMapping("indexFaq")
    public String indexFaq() {
        return this.mockConfig.getIndexFaq();
    }

    @GetMapping("indexHouse")
    public String indexHouse() {
        return this.mockConfig.getIndexHouse();
    }

    @GetMapping("infos/list")
    public String infoList(@RequestParam("type") Integer type) {
        switch (type) {
            case 1:
                return this.mockConfig.getInfoList1();
            case 2:
                return this.mockConfig.getInfoList2();
            case 3:
                return this.mockConfig.getInfoList3();
        }
        return this.mockConfig.getInfoList1();
    }

    @GetMapping("my")
    public String my() {
        return this.mockConfig.getMy();
    }
}
