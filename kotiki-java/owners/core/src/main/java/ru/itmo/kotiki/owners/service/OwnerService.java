package ru.itmo.kotiki.owners.service;

import ru.itmo.kotiki.dto.OwnerDto;

public interface OwnerService {

  OwnerDto findById(int ownerId);
}
