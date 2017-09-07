package tutorial.aluminiummod;

import java.io.IOException;

import javax.security.auth.login.FailedLoginException;

import cpw.mods.fml.client.config.GuiCheckBox;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiLabel;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;

public class SetupGUI extends GuiScreen {

	private TileEntityAluminium entity;
	private static GuiCheckBox mode1;
	private static GuiCheckBox mode2;
	private static GuiCheckBox alarmB;
	private static GuiButton set;
	private static GuiButton up1;
	private static GuiButton up2;
	private static GuiButton up3;
	private static GuiButton up4;
	private static GuiButton down1;
	private static GuiButton down2;
	private static GuiButton down3;
	private static GuiButton down4;
	private static String[] digits = {":","s1","s2","m1","m2","h1","h2"};
	public static final String INFO0 = "Clock mode";
	public static final String INFO1 = "Change output digit.   =>   h2 h1 : m2 m1 : s2 s1";
	public static final String INFO2 = "Set alarm.   =>   h2 h1 : m2 m1";
	public static final String INFO3 = "Timer mode";
	public static final String INFO4 = "Set count";
	public static final String INFO5 = "Back";
	public static final String UP = "UP";
	public static final String DOWN = "DOWN";
	
	private String digit = "s1";
	private String alarm = "00:00";
	private String count = "9";
	
	private int digitcount = 0;
	private int timercount = 9;
	private int alarmcount1 = 0;
	private int alarmcount2 = 0;
	
	private boolean clockFlag;
	private boolean timerFlag;
	private boolean alarmFlag;
	
	public SetupGUI(EntityPlayer player, TileEntityAluminium entity) {
		
		super();
		this.entity = entity;
		this.digit = digits[entity.getTimeIndex()];
		this.digitcount = entity.getTimeIndex();
		this.timercount = entity.getTimercount();
		this.alarmcount1 = entity.getAlarmcount1();
		this.alarmcount2 = entity.getAlarmcount2();
		this.alarm = this.getStrTime(alarmcount1, alarmcount2);
		this.count = String.valueOf(this.timercount);
		this.clockFlag = entity.isClock();
		this.timerFlag = entity.isTimer();
		this.alarmFlag = entity.isAlarm();
		
	}


	@Override
	public void initGui() {
		
		//クロックモードの設定
		mode1 = new GuiCheckBox(1, this.width / 8, this.height / 8, INFO0, entity.isClock());
		down1 = new GuiButton(4, this.width / 4, this.height / 8 + 40, 40, 20, DOWN);
		up1 = new GuiButton(5, this.width * 67 / 100, this.height / 8 + 40, 40, 20, UP);
		
		alarmB = new GuiCheckBox(10, this.width / 5, this.height / 8 + 80, INFO3, entity.isAlarm());
		
		up3 = new GuiButton(7, this.width / 4, this.height  / 8 + 100, 30, 20, UP);
		down3 = new GuiButton(8, this.width / 4 + 40, this.height / 8 + 100, 30, 20, DOWN);
		
		up4 = new GuiButton(9, this.width * 21 / 50 + 78, this.height / 8 + 100, 30, 20, UP);
		down4 = new GuiButton(6, this.width * 21 / 50 + 118, this.height / 8 + 100, 30, 20, DOWN);
		
		//タイマーモードの設定
		mode2 = new GuiCheckBox(2, this.width / 8, this.height * 67 / 100, INFO3, entity.isTimer());
		down2 = new GuiButton(6, this.width / 4, this.height * 67 / 100 + 40, 40, 20, DOWN);
		up2 = new GuiButton(7, this.width * 67 / 100, this.height * 67 / 100 + 40, 40, 20, UP);
		
		//完了の設定
		set = new GuiButton(3, this.width - this.width / 8, this.height - this.height / 8, 40, 20, INFO5);
		
		mode1.setIsChecked(entity.isClock());
		mode2.setIsChecked(entity.isTimer());
		alarmB.setIsChecked(entity.isAlarm());
		
		this.buttonList.add(mode1);
		this.buttonList.add(mode2);
		this.buttonList.add(set);
		this.buttonList.add(up1);
		this.buttonList.add(up2);
		this.buttonList.add(up3);
		this.buttonList.add(up4);
		this.buttonList.add(alarmB);
		this.buttonList.add(down1);
		this.buttonList.add(down2);
		this.buttonList.add(down3);
		this.buttonList.add(down4);
	}

	@Override
	protected void actionPerformed(GuiButton button) {
		if (button == this.mode1) {
			if(mode1.isChecked()){
				clockFlag = true;
				mode2.setIsChecked(false);
			}else{
				clockFlag = false;
				mode2.setIsChecked(true);
			}
	    }
		if (button == this.mode2) {
			if(mode2.isChecked()){
				timerFlag = true;
				mode1.setIsChecked(false);
			}else{
				timerFlag = false;
				mode1.setIsChecked(true);
			}
	    }
		
		if (button == this.alarmB) {
			if(alarmB.isChecked()){
				alarmFlag = true;
			}else{
				alarmFlag = false;
			}
	    }
		
		if(button == this.up1){
			if(this.digitcount == 6){
				this.digitcount = 0;
			}else{
				this.digitcount++;
			}
			this.digit = this.digits[this.digitcount];
		}
		if(button == this.down1){
			if(this.digitcount == 0){
				this.digitcount = 6;
			}else{
				this.digitcount--;
			}
			this.digit = this.digits[this.digitcount];
		}
		
		if(button == this.up2){
			if(this.timercount == 9){
				this.timercount = 0;
			}else{
				this.timercount++;
			}
			this.count = String.valueOf(this.timercount);
		}
		if(button == this.down2){
			if(this.timercount == 0){
				this.timercount = 9;
			}else{
				this.timercount--;
			}
			this.count = String.valueOf(this.timercount);
		}
		
		if(button == this.up3){
			if(this.alarmcount2 == 24){
				this.alarmcount2 = 0;
			}else{
				this.alarmcount2++;
			}
			this.alarm = getStrTime(alarmcount1, alarmcount2);
		}
		if(button == this.down3){
			if(this.alarmcount2 == 0){
				this.alarmcount2 = 24;
			}else{
				this.alarmcount2--;
			}
			this.alarm = getStrTime(alarmcount1, alarmcount2);
		}
		
		if(button == this.up4){
			if(this.alarmcount1 == 60){
				this.alarmcount1 = 0;
			}else{
				this.alarmcount1++;
			}
			this.alarm = getStrTime(alarmcount1, alarmcount2);
		}
		if(button == this.down4){
			if(this.alarmcount1 == 0){
				this.alarmcount1 = 60;
			}else{
				this.alarmcount1--;
			}
			this.alarm = getStrTime(alarmcount1, alarmcount2);
		}
		
//		if(button == this.up3){
//			if(this.alarmcount1 == 60){
//				this.alarmcount1 = 0;
//				if(this.alarmcount2 == 24){
//					this.alarmcount2 = 0;
//				}else{
//					this.alarmcount2++;
//				}
//			}else{
//				this.alarmcount1++;
//			}
//			this.alarm = getStrTime(alarmcount1, alarmcount2);
//		}
//		if(button == this.down3){
//			if(this.alarmcount1 == 0){
//				this.alarmcount1 = 60;
//				if(this.alarmcount2 == 0){
//					this.alarmcount2 = 24;
//				}else{
//					this.alarmcount2--;
//				}
//			}else{
//				this.alarmcount1--;
//			}
//			this.alarm = getStrTime(alarmcount1, alarmcount2);
//		}
		//終了処理
	    if (button == this.set){
	    	this.entity.setTimeIndex(digitcount);
	    	this.entity.setTimercount(timercount);
	    	this.entity.setAlarmcount1(alarmcount1);
	    	this.entity.setAlarmcount2(alarmcount2);
	    	this.entity.setClock(clockFlag);
	    	this.entity.setTimer(timerFlag);
	    	this.entity.setAlarm(alarmFlag);
	        //Main.packetHandler.sendToServer(...);
	        this.mc.displayGuiScreen(null);
	        if (this.mc.currentScreen == null)
	            this.mc.setIngameFocus();
	    }
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTicks) {
	    this.drawDefaultBackground();
	    super.drawScreen(mouseX, mouseY, partialTicks);
	    
	    this.drawString(this.fontRendererObj, INFO1, this.width / 5, this.height / 8 + 20, 0xffffff);
//	    this.drawString(this.fontRendererObj, INFO2, this.width / 5, this.height / 8 + 80, 0xffffff);
	    this.drawString(this.fontRendererObj, INFO4, this.width / 5, this.height  * 67 / 100 + 20, 0xffffff);
	    
	    this.drawString(this.fontRendererObj, digit, this.width / 2 - 5, this.height / 8 + 40 + 7, 0xffffff);
	    this.drawString(this.fontRendererObj, alarm, this.width / 2 - 13, this.height / 8 + 100 + 7, 0xffffff);
	    this.drawString(this.fontRendererObj, count, this.width / 2, this.height * 67 / 100 + 40 + 7, 0xffffff);
	    
	}
	
	@Override
	public boolean doesGuiPauseGame() {
	    return true;
	}
	
	public String getStrTime(int a1, int a2){
		String str1;
		if(a1/10 == 0){
			str1 = "0" + String.valueOf(a1);
		}else{
			str1 = String.valueOf(a1);
		}
		String str2;
		if(a2/10 == 0){
			str2 = "0" + String.valueOf(a2);
		}else{
			str2 = String.valueOf(a2);
		}
		
		return (str2 + ":" + str1);
		
	}
	
	

}