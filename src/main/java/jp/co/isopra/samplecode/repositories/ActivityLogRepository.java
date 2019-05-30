package jp.co.isopra.samplecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.isopra.samplecode.entity.ActivityLog;

/**
 * activity_logテーブルへアクセスするためのリポジトリクラス
 * @author masumi.sato
 *
 */
@Repository
public interface ActivityLogRepository extends JpaRepository<ActivityLog, String> {
}
