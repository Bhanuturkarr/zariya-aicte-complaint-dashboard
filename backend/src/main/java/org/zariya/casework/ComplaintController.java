package org.zariya.casework;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDate;
import java.util.List;

@RestController @RequestMapping("/api/complaints")
public class ComplaintController {
    private final ComplaintRepository repository;
    public ComplaintController(ComplaintRepository repository){this.repository=repository;}
    public record ComplaintInput(@NotBlank String caseRef,@NotBlank String institutionId,@NotBlank String institution,@NotBlank String title,@NotBlank String bureau,@NotBlank String category,String priority,String status,String officer,LocalDate dueDate){}
    @GetMapping public List<Complaint> list(@RequestParam(required=false) String institutionId){return institutionId==null?repository.findAll():repository.findByInstitutionIdIgnoreCase(institutionId);}
    @GetMapping("/{id}") public Complaint get(@PathVariable Long id){return repository.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));}
    @PostMapping @ResponseStatus(HttpStatus.CREATED) public Complaint create(@Valid @RequestBody ComplaintInput x){return repository.save(new Complaint(x.caseRef(),x.institutionId(),x.institution(),x.title(),x.bureau(),x.category(),x.priority()==null?"Medium":x.priority(),x.status()==null?"Under review":x.status(),x.officer(),x.dueDate()));}
}
