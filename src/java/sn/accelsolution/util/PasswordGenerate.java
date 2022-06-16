/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sn.accelsolution.util;

import java.util.Random;

/**
 *
 * @author DV7
 */
public class PasswordGenerate {

    public PasswordGenerate() {

    }

    public String generatePwd1() {
        String baseTxt = "KZ?";
        String randomCharacter = this.randowSpecialeCharacter();
        String result = baseTxt + randomCharacter;
        return result;
    }

    public String randowSpecialeCharacter() {
        Random r = new Random();
        char[] choices = ("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890$#_@/?|").toCharArray();
        StringBuilder salt = new StringBuilder(8);
        for (int i = 0; i < 8; ++i) {
            salt.append(choices[r.nextInt(choices.length)]);
        }
        return salt.toString();
    }

    public String generatePassword2() {
        StringBuffer randPass = new StringBuffer();
        char ch;
        String CHAR_L ="abcdefghijklmnopqrstuvwxyz";
        String CHAR_U="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String NUM="1234567890";
        String CHAR_S="!@#$%^&*()_=+";
        int PASSWORD_LENGTH = 8;

        for (int i = 0; i < PASSWORD_LENGTH; i++) {
            if (randPass.length() < PASSWORD_LENGTH) {
                ch = CHAR_L.charAt(getRandomNumber(CHAR_L.length()));
                randPass.append(ch);
            }
            if (randPass.length() < PASSWORD_LENGTH) {
                ch = CHAR_U.charAt(getRandomNumber(CHAR_U.length()));
                randPass.append(ch);
            }
            if (randPass.length() < PASSWORD_LENGTH) {
                ch = NUM.charAt(getRandomNumber(NUM.length()));
                randPass.append(ch);
            }
            if (randPass.length() < PASSWORD_LENGTH) {
                ch = CHAR_S.charAt(getRandomNumber(CHAR_S.length()));
                randPass.append(ch);
            }
        }
        return randPass.toString();
    }

    private int getRandomNumber(int index) {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(index - 1);
        return randomInt;
    }
}
