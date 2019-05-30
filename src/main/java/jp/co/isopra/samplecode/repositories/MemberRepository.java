package jp.co.isopra.samplecode.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jp.co.isopra.samplecode.entity.Member;

/**
 * Memberテーブルへアクセスするためのリポジトリクラス
 * @author masumi.sato
 *
 */
@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
}
