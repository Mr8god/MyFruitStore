package cn.mr8god.fruitweb.controller;

import cn.mr8god.fruitweb.model.Fruit;
import cn.mr8god.fruitweb.service.FruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * @author Mr8god
 * @date 2020年11月26日00:57:51
 * @time 2020年11月26日00:57:54
 */
@Controller
public class FruitController {
    private FruitService fruitService;
    @Autowired
    public void setFruitService(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GetMapping("toFruitAdd")
    public String toAdd() {
        return "fruitAdd";
    }
    @GetMapping("/fruitAdd")
    public String add(Fruit fruit) {
        boolean ret = fruitService.saveFruit(fruit);
        if (ret) {
            return "redirect:fruitList";
        } else {
            return "fruitAdd";
        }
    }

    @GetMapping("/fruitDel")
    public String del(int id) {
        fruitService.delFruitById(id);
        return "redirect:fruitList";
    }

    @GetMapping("/fruitEdit")
    public String toEdit(int id, Model model) {
        Fruit fruit = fruitService.findFruitById(id);
        model.addAttribute("fruit", fruit);
        return "fruitEdit";
    }

    @PostMapping("/fruitEdit")
    public String doEdit(Fruit fruit) {
        boolean ret = fruitService.updateFruit(fruit);
        if (ret) {
            return "redirect:fruitList";
        } else {
            return "fruitEdit";
        }
    }

    @GetMapping("/fruitList")
    public String findAll(Model model) {
        List<Fruit> fruits = fruitService.findAllFruits();
        model.addAttribute("fruits", fruits);
        return "fruitList";
    }
}
