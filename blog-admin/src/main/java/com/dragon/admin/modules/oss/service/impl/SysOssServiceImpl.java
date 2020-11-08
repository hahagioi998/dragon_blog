/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.dragon.admin.modules.oss.service.impl;

import com.dragon.admin.feign.ThirdPartyFeignService;
import com.dragon.admin.modules.oss.dao.SysOssDao;
import com.dragon.admin.modules.oss.entity.SysOssEntity;
import com.dragon.admin.modules.oss.service.SysOssService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dragon.admin.common.utils.PageUtils;
import com.dragon.admin.common.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service("sysOssService")
public class SysOssServiceImpl extends ServiceImpl<SysOssDao, SysOssEntity> implements SysOssService {

	@Autowired
	ThirdPartyFeignService thirdPartyFeignService;

	@Override
	public PageUtils queryPage(Map<String, Object> params) {
		IPage<SysOssEntity> page = this.page(
			new Query<SysOssEntity>().getPage(params)
		);

		return new PageUtils(page);
	}

	@Override
	public boolean saveBatchFile(Map<String, String> fileMap) {
		List<SysOssEntity> ossEntityList = new ArrayList<>();
		for (String key : fileMap.keySet()) {
			SysOssEntity ossEntity = new SysOssEntity();
			ossEntity.setFileName(key);
			ossEntity.setUrl(fileMap.get(key));
			ossEntity.setCreateDate(new Date());
			ossEntityList.add(ossEntity);
		}
		return this.saveBatch(ossEntityList);
	}

	@Override
	public void removeFileByIds(Map<String, Object> data) {
		List ids = (List) data.get("ids");
		List url = (List) data.get("fileNames");


	}

}
