package com.spend.track.transaction.category;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<CategoryMapping> saveCategoriesFromCsv(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String completeData = new String(bytes);
        String[] rows = completeData.split("\n");

        // TODO: Optimize for large amount of data set later on (Replace saveALl with insert or update)
        Set<CategoryMapping> mappings = new HashSet<>(categoryRepository.findAll());
        for (String row : rows) {
            String[] cols = row.split(",");
            mappings.add(new CategoryMapping(cols[0], TransactionCategory.valueOf(cols[1])));
        }
        categoryRepository.saveAll(mappings);
        return new ArrayList<>(mappings);
    }
}
