package jp.co.isopra.samplecode.service;

import java.time.LocalDateTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.isopra.samplecode.entity.ActivityLog;
import jp.co.isopra.samplecode.repositories.ActivityLogRepository;

@Service
@Transactional
public class ActivityLogService {

	@Autowired
	private ActivityLogRepository logRepository;

	/**
	 * ログ情報をDBに登録。
	 */
	public void registerLog(String activity, String login_id) {

		// エンティティ作成
		ActivityLog log = new ActivityLog();
		log.setActivity(activity);
		log.setLogin_id(login_id);
		log.setCreated_at(LocalDateTime.now());
		// 保存
		logRepository.save(log);
	}
}
