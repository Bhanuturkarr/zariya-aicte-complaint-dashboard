package org.zariya.overlap;

import org.zariya.casework.Complaint;
import org.zariya.casework.ComplaintRepository;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/overlaps")
public class OverlapController {
    private final ComplaintRepository repository;
    public OverlapController(ComplaintRepository repository){this.repository=repository;}
    public record Candidate(Complaint first,Complaint second,int score,List<String> factors){}
    @GetMapping public List<Candidate> findCandidates(){List<Complaint> rows=repository.findAll();List<Candidate> out=new ArrayList<>();for(int i=0;i<rows.size();i++)for(int j=i+1;j<rows.size();j++){var result=OverlapScorer.score(rows.get(i),rows.get(j));if(result.score()>=55)out.add(new Candidate(rows.get(i),rows.get(j),result.score(),result.factors()));}return out;}
}
