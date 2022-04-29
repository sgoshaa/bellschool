package com.bell.bellschooll.service;

import com.bell.bellschooll.dto.response.DocumentDto;

import java.util.List;

/**
 * Интерфейс для работы с Document
 */
public interface DocumentService {
    /**
     * Метод возвращает список всех документов
     *
     * @return List DocumentDto
     */
    List<DocumentDto> getAllDocument();
}
