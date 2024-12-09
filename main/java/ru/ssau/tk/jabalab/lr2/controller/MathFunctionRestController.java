package ru.ssau.tk.jabalab.lr2.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ssau.tk.jabalab.lr2.DTO.MathFunctionDTO;
import ru.ssau.tk.jabalab.lr2.services.MathFunctionService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("lab/math-functions")
public class MathFunctionRestController {

    private final MathFunctionService mathFunctionService;

    @GetMapping("list")
    public ResponseEntity<List<MathFunctionDTO>> findAllFunctions(
            @RequestParam(name = "functionType", required = false) String functionType
    ) {
        List<MathFunctionDTO> functionDTOList = this.mathFunctionService.findAllFunctions(functionType);
        if (functionDTOList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(functionDTOList);
    }

    @PostMapping
    public ResponseEntity<MathFunctionDTO> create(@RequestBody MathFunctionDTO functionDTO) {
        MathFunctionDTO createdFunction = this.mathFunctionService.create(functionDTO);

        return ResponseEntity.ok(createdFunction);
    }

    @GetMapping("{functionId:\\d+}")
    public ResponseEntity<MathFunctionDTO> read(@PathVariable int functionId) {
        MathFunctionDTO functionDTO = this.mathFunctionService.read(functionId);
        if (functionDTO == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(functionDTO);
    }

    @PatchMapping("{functionId:\\d+}")
    public ResponseEntity<MathFunctionDTO> update(@RequestBody MathFunctionDTO functionDTO, @PathVariable int functionId) {
        functionDTO.setId(functionId);
        MathFunctionDTO editedFunction = this.mathFunctionService.update(functionDTO);

        return ResponseEntity.ok(editedFunction);
    }

    @DeleteMapping("{functionId:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable int functionId) {
        this.mathFunctionService.delete(functionId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("")
    public String Hello() {return "Hello world";}

}