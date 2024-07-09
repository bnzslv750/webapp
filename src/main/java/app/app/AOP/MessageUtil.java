package app.app.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MessageUtil {
	@Before("execution(* app.*.*.*.*(..))")
	public void startMethod(JoinPoint joinPoint) {
		// 各メソッドの開始時にログを出力
		System.out.println("***** メソッド開始：" + joinPoint.getSignature());
	}
	
	@After("execution(* app.*.*.*.*(..))")
	public void endMethod(JoinPoint joinPoint) {
		// 各メソッドの終了時にログを出力
		System.out.println("***** メソッド修了：" + joinPoint.getSignature());
	}
	
	@AfterReturning("execution(* *..*LoginController.index(..))")
	public void afterLogin() {
		// ログイン成功時にログを出力
		System.out.println("***** ログインに成功しました。");
	}
	
	@AfterThrowing("execution(* *..*(..))")
	public void afterThrowing(Throwable e) {
		// 例外検出時にログを出力
		System.out.println("***** 例外を検出しました。：");
		System.out.println(e.getMessage());
		System.out.println("*****");
	}
}
