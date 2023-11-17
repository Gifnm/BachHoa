package com.spring.main.util;

import com.spring.main.model.Employee;

public class SessionAttr {

	public static String CURRENT_MESSAGE = "Chào mừng quay lại !";
	public static String DEFAULT_COLOR;
	public static String USERNAME_LOGIN;
	public static Employee USER_INFO;
	
	// For Toasts - Message - Alert
	
	// ---> Warning
	public static String Warning_Toast = "warning";
	public static String Warning_Icon = "warning__icon";
	public static String Warning_Show_Icon = "<svg xmlns=\"http://www.w3.org/2000/svg\" width=\"24\" viewBox=\"0 0 24 24\" height=\"24\" fill=\"none\"><path fill=\"#393a37\" d=\"m13 14h-2v-5h2zm0 4h-2v-2h2zm-12 3h22l-11-19z\"></path></svg>";
	public static String Warning_Title = "warning__title";
	public static String Warning_Close = "warning__close";
	// --> Info
	public static String Info_Toast = "info";
	public static String Info_Icon = "info__icon";
	public static String Info_Show_Icon = "<svg fill=\"none\" height=\"24\" viewBox=\"0 0 24 24\" width=\"24\" xmlns=\"http://www.w3.org/2000/svg\"><path d=\"m12 1.5c-5.79844 0-10.5 4.70156-10.5 10.5 0 5.7984 4.70156 10.5 10.5 10.5 5.7984 0 10.5-4.7016 10.5-10.5 0-5.79844-4.7016-10.5-10.5-10.5zm.75 15.5625c0 .1031-.0844.1875-.1875.1875h-1.125c-.1031 0-.1875-.0844-.1875-.1875v-6.375c0-.1031.0844-.1875.1875-.1875h1.125c.1031 0 .1875.0844.1875.1875zm-.75-8.0625c-.2944-.00601-.5747-.12718-.7808-.3375-.206-.21032-.3215-.49305-.3215-.7875s.1155-.57718.3215-.7875c.2061-.21032.4864-.33149.7808-.3375.2944.00601.5747.12718.7808.3375.206.21032.3215.49305.3215.7875s-.1155.57718-.3215.7875c-.2061.21032-.4864.33149-.7808.3375z\" fill=\"#393a37\"></path></svg>";
	public static String Info_Title = "info__title";
	public static String Info_Close = "info__close";
	// --> Error
	public static String Error_Toast = "error";
	public static String Error_Icon = "error__icon";
	public static String Error_Show_Icon = "<svg fill=\"none\" height=\"24\" viewBox=\"0 0 24 24\" width=\"24\" xmlns=\"http://www.w3.org/2000/svg\"><path d=\"m13 13h-2v-6h2zm0 4h-2v-2h2zm-1-15c-1.3132 0-2.61358.25866-3.82683.7612-1.21326.50255-2.31565 1.23915-3.24424 2.16773-1.87536 1.87537-2.92893 4.41891-2.92893 7.07107 0 2.6522 1.05357 5.1957 2.92893 7.0711.92859.9286 2.03098 1.6651 3.24424 2.1677 1.21325.5025 2.51363.7612 3.82683.7612 2.6522 0 5.1957-1.0536 7.0711-2.9289 1.8753-1.8754 2.9289-4.4189 2.9289-7.0711 0-1.3132-.2587-2.61358-.7612-3.82683-.5026-1.21326-1.2391-2.31565-2.1677-3.24424-.9286-.92858-2.031-1.66518-3.2443-2.16773-1.2132-.50254-2.5136-.7612-3.8268-.7612z\" fill=\"#393a37\"></path></svg>";
	public static String Error_Title = "error__title";
	public static String Error_Close = "error__close";
	// --> Success
	public static String Success_Toast = "success";
	public static String Success_Icon = "success__icon";
	public static String Success_Show_Icon = "<svg fill=\"none\" height=\"24\" viewBox=\"0 0 24 24\" width=\"24\" xmlns=\"http://www.w3.org/2000/svg\"><path clip-rule=\"evenodd\" d=\"m12 1c-6.075 0-11 4.925-11 11s4.925 11 11 11 11-4.925 11-11-4.925-11-11-11zm4.768 9.14c.0878-.1004.1546-.21726.1966-.34383.0419-.12657.0581-.26026.0477-.39319-.0105-.13293-.0475-.26242-.1087-.38085-.0613-.11844-.1456-.22342-.2481-.30879-.1024-.08536-.2209-.14938-.3484-.18828s-.2616-.0519-.3942-.03823c-.1327.01366-.2612.05372-.3782.1178-.1169.06409-.2198.15091-.3027.25537l-4.3 5.159-2.225-2.226c-.1886-.1822-.4412-.283-.7034-.2807s-.51301.1075-.69842.2929-.29058.4362-.29285.6984c-.00228.2622.09851.5148.28067.7034l3 3c.0983.0982.2159.1748.3454.2251.1295.0502.2681.0729.4069.0665.1387-.0063.2747-.0414.3991-.1032.1244-.0617.2347-.1487.3236-.2554z\" fill=\"#393a37\" fill-rule=\"evenodd\"></path></svg>";
	public static String Success_Title = "success__title";
	public static String Success_Close = "success__close";
	
	// --> Custom
	public static String Toast = Info_Toast;
	public static String Icon = Info_Icon;
	public static String Show_Icon = Info_Show_Icon;
	public static String Title = Info_Title;
	public static String Close = Info_Close;
	
}
