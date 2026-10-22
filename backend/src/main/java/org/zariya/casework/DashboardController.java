package org.zariya.casework;

import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController @RequestMapping("/api/dashboard")
public class DashboardController {
    private final ComplaintRepository repository;
    public DashboardController(ComplaintRepository repository){this.repository=repository;}
    @GetMapping("/summary") public Map<String,Long> summary(){var rows=repository.findAll();long open=rows.stream().filter(c->!c.getStatus().equalsIgnoreCase("Resolved")&&!c.getStatus().equalsIgnoreCase("Closed")).count();long overdue=rows.stream().filter(c->c.getStatus().equalsIgnoreCase("Overdue")).count();long resolved=rows.size()-open;return Map.of("total",(long)rows.size(),"open",open,"overdue",overdue,"resolved",resolved);}
}
