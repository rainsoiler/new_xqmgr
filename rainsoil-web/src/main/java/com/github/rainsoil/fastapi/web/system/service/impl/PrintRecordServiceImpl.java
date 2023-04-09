package com.github.rainsoil.fastapi.web.system.service.impl;

import com.github.rainsoil.fastapi.web.system.entity.PrintRecord;
import com.github.rainsoil.fastapi.web.system.mapper.PrintRecordMapper;
import com.github.rainsoil.fastapi.web.system.service.IPrintRecordService;
import com.github.rainsoil.fastapi.common.core.mybatis.BaseServiceImpl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
/**
 * <p>
 * 打印记录 服务实现类
 * </p>
 *
 * @author luyanan
 * @since 2023-04-08
 */
@RequiredArgsConstructor
@Service
public class PrintRecordServiceImpl extends BaseServiceImpl<PrintRecordMapper, PrintRecord> implements IPrintRecordService {

}
