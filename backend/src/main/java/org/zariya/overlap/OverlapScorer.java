package org.zariya.overlap;

import org.zariya.casework.Complaint;
import java.util.*;

/** Explainable heuristic; emits a score only and never merges complaints. */
public final class OverlapScorer {
    private OverlapScorer() {}
    public record Result(int score,List<String> factors) {}
    public static Result score(Complaint a, Complaint b) {
        int score=0; List<String> factors=new ArrayList<>();
        if(a.getInstitutionId().equalsIgnoreCase(b.getInstitutionId())){score+=40;factors.add("Same institution ID");}
        if(a.getCategory().equalsIgnoreCase(b.getCategory())){score+=20;factors.add("Same complaint category");}
        Set<String> words=new HashSet<>(tokens(a.getTitle())); Set<String> other=tokens(b.getTitle());
        if(!words.isEmpty()&&!other.isEmpty()){Set<String> common=new HashSet<>(words);common.retainAll(other);double ratio=(double)common.size()/Math.max(words.size(),other.size());if(ratio>=.35){score+=(int)Math.round(Math.min(40,ratio*40));factors.add("Similar title terms");}}
        return new Result(Math.min(score,100),List.copyOf(factors));
    }
    private static Set<String> tokens(String value){Set<String> out=new HashSet<>();for(String s:value.toLowerCase(Locale.ROOT).replaceAll("[^a-z0-9 ]"," ").split("\\s+"))if(s.length()>2)out.add(s);return out;}
}
