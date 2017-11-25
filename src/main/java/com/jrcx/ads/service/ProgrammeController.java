package com.jrcx.ads.service;

import com.jrcx.ads.domain.Programme;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Administrator.
 * @date 2017/11/25
 */
@RestController
@RequestMapping(value = "/api/programmes")  // 通过这里配置使下面的映射都在/api/programmes下
public class ProgrammeController {
    //创建线程安全的Map
    static Map<Long, Programme> programmes = Collections.synchronizedMap(new HashMap<Long, Programme>());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Programme> getProgrammeList(){
        // 处理"/api/programmes/"的GET请求，用来获取节目列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<Programme> r = new ArrayList<Programme>(programmes.values());
        return r;
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public String postUser(@ModelAttribute Programme programme){
        // 处理"/api/programmes/"的POST请求，用来创建User
        // 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
        programmes.put(programme.getId(), programme);
        return "success";
    }


    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public Programme getUser(@PathVariable Long id) {
        // 处理"/api/programmes/{id}"的GET请求，用来获取url中id值的User信息
        // url中的id可通过@PathVariable绑定到函数的参数中
        return programmes.get(id);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public String putUser(@PathVariable Long id, @ModelAttribute Programme programme) {
        // 处理"/api/programmes/{id}"的PUT请求，用来更新Programme信息
        Programme p = programmes.get(id);
        p.setName(programme.getName());
        p.setSn(programme.getSn());
        programmes.put(id, p);
        return "success";
    }

    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public String deleteUser(@PathVariable Long id) {
        // 处理"/api/programmes/{id}"的DELETE请求，用来删除Programme
        programmes.remove(id);
        return "success";
    }
}
