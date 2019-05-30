package jp.co.isopra.samplecode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.isopra.samplecode.entity.Member;
import jp.co.isopra.samplecode.service.ActivityLogService;
import jp.co.isopra.samplecode.service.MemberRegistrationService;

@Controller
public class MemberRegistrationController {

	@Autowired
	private MemberRegistrationService service;
	@Autowired
	private ActivityLogService logService;

	/**
	 * メンバー登録の処理
	 * @param login_id ログインID
	 * @param password パスワード
	 * @param nickname 表示用氏名
	 * @param model 遷移先画面にセットするModel
	 * @return 遷移先画面名
	 */
	@RequestMapping("/member/register")
	public String registerMember(
			@RequestParam String login_id,
			@RequestParam String password,
			@RequestParam String nickname,
			Model model) {

//		// DEBUG parameter出力
//		java.lang.System.out.println("member regist:" + login_id + "/" + password + "/" + nickname);

		//memberテーブルにinsertする時の引数。
		Member entity = new Member();

		entity.setLogin_id(login_id);
		entity.setPassword(password);
		entity.setNickname(nickname);

		//memberテーブルにinsertする。
		service.registerMember(entity);

		model.addAttribute("message", "登録しました");

		// ログの記録
		logService.registerLog("member_registed", login_id);

		return "login";
	}
}
