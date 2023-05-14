package com.stc.assignment.service;

import com.stc.assignment.model.Item;
import com.stc.assignment.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    public void createItem(Item item){
        itemRepository.save(item);
    }
    public Item findItemById(Long id){
        return itemRepository.getOne(id);
    }



}
