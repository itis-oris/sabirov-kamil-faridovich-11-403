package ru.itis.dis403.questplatform.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.dis403.questplatform.converter.QuestToQuestDTOConverter;
import ru.itis.dis403.questplatform.dto.QuestDTO;
import ru.itis.dis403.questplatform.form.QuestForm;
import ru.itis.dis403.questplatform.service.QuestService;

import java.util.List;

@RestController
@RequestMapping("/api/quests")
@RequiredArgsConstructor
public class QuestRestController {
    private final QuestService questService;
    private final QuestToQuestDTOConverter converter;

    @GetMapping
    @Operation(summary = "Получить список квестов")
    @ApiResponse(responseCode = "200", description = "Успешно")
    public ResponseEntity<List<QuestDTO>> getAll() {
        return ResponseEntity.ok(questService.getAllQuestsAsDTO());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить квест по ID")
    public ResponseEntity<QuestDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(converter.convert(questService.getQuestById(id)));
    }

    @PostMapping
    @Operation(summary = "Создать квест")
    public ResponseEntity<QuestDTO> create(@RequestBody QuestForm form) {
        return ResponseEntity.ok(converter.convert(questService.create(form)));
    }

}