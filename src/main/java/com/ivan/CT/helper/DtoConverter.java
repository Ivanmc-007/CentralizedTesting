package com.ivan.CT.helper;

import com.ivan.CT.entities.Permission;
import com.ivan.CT.entities.dto.PermissionDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

@Component
public class DtoConverter {

    private final ModelMapper modelMapper;

    public DtoConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Permission convertToEntity(PermissionDto permissionDto) throws ParseException {
        Permission permission = modelMapper.map(permissionDto, Permission.class);
        Date dateTime = MyDateParser.convertStringAsDateTimeToDate(permissionDto.getDate()
                + " " + permissionDto.getTime());
        permission.setDateStartTest(dateTime);
        return permission;
    }
}
