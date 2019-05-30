package jp.co.isopra.samplecode.service;

import java.util.Collection;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.isopra.samplecode.entity.AccountDetails;
import jp.co.isopra.samplecode.entity.Member;
import jp.co.isopra.samplecode.repositories.MemberRepository;

/**
 * ログイン処理のサービスクラス
 * @author masumi.sato
 *
 */
@Service
public class LoginService implements UserDetailsService {

	@Autowired
	private MemberRepository repository;

	@Autowired
	private ActivityLogService logService;

	@Override
	public UserDetails loadUserByUsername(String login_id)
			throws UsernameNotFoundException {

//		// DEBUG parameter出力
//		java.lang.System.out.println("login:" + login_id);


		//DBからユーザ情報を取得。
		Member member = repository.findById(login_id).orElse(null);

		if (Objects.isNull(member)) {
			// DEBUG
			java.lang.System.out.println("login:" + login_id + " NG");

			// ユーザ情報が見つからなかった場合は例外を返却
			throw new UsernameNotFoundException("User not found.");
		}

		// DEBUG
		java.lang.System.out.println("login:" + login_id + " OK");

		// ログの記録
		logService.registerLog("login", member.getLogin_id());

		return new AccountDetails(member, getAuthorities(member));
	}

	/**
	 * 認証が通った時にこのユーザに与える権限の範囲を設定する。
	 * @param account DBから取得したユーザ情報。
	 * @return 権限の範囲のリスト。
	 */
	private Collection<GrantedAuthority> getAuthorities(Member member) {
		//認可が通った時にこのユーザに与える権限の範囲を設定する。
		return AuthorityUtils.createAuthorityList("ROLE_MEMBER");
	}

}
