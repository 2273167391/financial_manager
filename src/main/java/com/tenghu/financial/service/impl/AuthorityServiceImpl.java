package com.tenghu.financial.service.impl;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tenghu.financial.mapper.AuthorityMapper;
import com.tenghu.financial.model.Authority;
import com.tenghu.financial.model.page.PageBean;
import com.tenghu.financial.service.IAuthorityService;
import com.tenghu.financial.utils.JsonMessageUtil;

/**
 * 权限服务实现类
 * @author Arvin_Li
 *
 */

@Service
public class AuthorityServiceImpl implements IAuthorityService{
	private final Logger log=LoggerFactory.getLogger(AuthorityServiceImpl.class);
	
	@Autowired
	private AuthorityMapper authorityMapper;

	@Override
	public List<Authority> queryAllAuthority() {
		return authorityMapper.queryAllAuthority();
	}

	@Override
	public Authority queryAuthorityById(int id) {
		return authorityMapper.queryAuthorityById(id);
	}

	@Override
	public List<Authority> queryAuthorityByIds(String[] ids) {
		return authorityMapper.queryAuthorityByIds(ids);
	}

	@Override
	public PageBean<Authority> queryAuthorityPage(PageBean<Authority> pageBean) {
		//获取总记录数
		int totalCount=authorityMapper.queryAllAuthority().size();
		//获取权限
		List<Authority> authList=authorityMapper.queryAuthorityPage(pageBean);
		pageBean.setTotalCount(totalCount);
		pageBean.setShowRecords(authList);
		return pageBean;
	}

	@Override
	public String addAuth(Authority auth, int parentId) {
		try {
			//获取父级权限
			Authority parentAuth=queryAuthorityById(parentId);
			auth.setParentAuth(parentAuth);//设置父级权限
			auth.setLevel(parentAuth.getLevel()+1);
			auth.setCreateTime(new Date());//创建时间
			//添加
			int result=authorityMapper.addAuth(auth);
			return result>0?JsonMessageUtil.getSuccessJSON("添加成功"):JsonMessageUtil.getErrorJSON("添加失败");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("AuthorityServiceImpl->addAuth:"+e.getMessage());
			return JsonMessageUtil.getErrorJSON("系统异常，请稍后再试！");
		}
		
	}

	@Override
	public String deleteAuth(int authId) {
		try {
			//查询下级权限
			int childAuthNum=authorityMapper.queryChildAuthorityById(authId).size();
			if(childAuthNum>0)
				return JsonMessageUtil.getErrorJSON("该权限存在下级权限，删除失败！");
			//删除权限
			int result=authorityMapper.deleteAuth(authId);
			return result>0?JsonMessageUtil.getSuccessJSON("删除成功"):JsonMessageUtil.getErrorJSON("删除失败");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("AuthorityServiceImpl->deleteAuth:"+e.getMessage());
			return JsonMessageUtil.getErrorJSON("系统异常，请稍后再试！");
		}
	}

	@Override
	public String updateAuth(Authority auth) {
		try {
			//修改权限
			int result=authorityMapper.updateAuth(auth);
			return result>0?JsonMessageUtil.getSuccessJSON("修改成功"):JsonMessageUtil.getErrorJSON("修改失败");
		} catch (Exception e) {
			e.printStackTrace();
			log.debug("AuthorityServiceImpl->updateAuth:"+e.getMessage());
			return JsonMessageUtil.getErrorJSON("系统异常，请稍后再试！");
		}
	}
}
