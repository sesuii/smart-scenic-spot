package com.smartscenicspot.mapper;

import com.smartscenicspot.pojo.Showplace;
import com.smartscenicspot.dto.ShowplaceDto;
import com.smartscenicspot.vo.ShowplaceVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author <a href="mailto: sjiahui27@gmail.com">songjiahui</a>
 * @since 2023/3/31 12:01
 **/
@Mapper
public interface ShowplaceMapper {

    ShowplaceMapper INSTANCE = Mappers.getMapper(ShowplaceMapper.class);

    ShowplaceDto toDto(Showplace showplace);

    List<ShowplaceDto> toDtos(List<Showplace> showplaces);

    Showplace voToPojo(ShowplaceVo showplaceVo);
}
