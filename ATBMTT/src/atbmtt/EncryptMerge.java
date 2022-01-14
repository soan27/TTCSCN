/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atbmtt;

import com.sun.javafx.util.TempState;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.omg.CORBA.INTERNAL;

/**
 *
 * @author NgaDo
 */
public class EncryptMerge {

    char[] sbox = {//256 phan tu
        0x63, 0x7c, 0x77, 0x7b, 0xf2, 0x6b, 0x6f, 0xc5, 0x30, 0x01, 0x67, 0x2b, 0xfe, 0xd7, 0xab, 0x76,
        0xca, 0x82, 0xc9, 0x7d, 0xfa, 0x59, 0x47, 0xf0, 0xad, 0xd4, 0xa2, 0xaf, 0x9c, 0xa4, 0x72, 0xc0,
        0xb7, 0xfd, 0x93, 0x26, 0x36, 0x3f, 0xf7, 0xcc, 0x34, 0xa5, 0xe5, 0xf1, 0x71, 0xd8, 0x31, 0x15,
        0x04, 0xc7, 0x23, 0xc3, 0x18, 0x96, 0x05, 0x9a, 0x07, 0x12, 0x80, 0xe2, 0xeb, 0x27, 0xb2, 0x75,
        0x09, 0x83, 0x2c, 0x1a, 0x1b, 0x6e, 0x5a, 0xa0, 0x52, 0x3b, 0xd6, 0xb3, 0x29, 0xe3, 0x2f, 0x84,
        0x53, 0xd1, 0x00, 0xed, 0x20, 0xfc, 0xb1, 0x5b, 0x6a, 0xcb, 0xbe, 0x39, 0x4a, 0x4c, 0x58, 0xcf,
        0xd0, 0xef, 0xaa, 0xfb, 0x43, 0x4d, 0x33, 0x85, 0x45, 0xf9, 0x02, 0x7f, 0x50, 0x3c, 0x9f, 0xa8,
        0x51, 0xa3, 0x40, 0x8f, 0x92, 0x9d, 0x38, 0xf5, 0xbc, 0xb6, 0xda, 0x21, 0x10, 0xff, 0xf3, 0xd2,
        0xcd, 0x0c, 0x13, 0xec, 0x5f, 0x97, 0x44, 0x17, 0xc4, 0xa7, 0x7e, 0x3d, 0x64, 0x5d, 0x19, 0x73,
        0x60, 0x81, 0x4f, 0xdc, 0x22, 0x2a, 0x90, 0x88, 0x46, 0xee, 0xb8, 0x14, 0xde, 0x5e, 0x0b, 0xdb,
        0xe0, 0x32, 0x3a, 0x0a, 0x49, 0x06, 0x24, 0x5c, 0xc2, 0xd3, 0xac, 0x62, 0x91, 0x95, 0xe4, 0x79,
        0xe7, 0xc8, 0x37, 0x6d, 0x8d, 0xd5, 0x4e, 0xa9, 0x6c, 0x56, 0xf4, 0xea, 0x65, 0x7a, 0xae, 0x08,
        0xba, 0x78, 0x25, 0x2e, 0x1c, 0xa6, 0xb4, 0xc6, 0xe8, 0xdd, 0x74, 0x1f, 0x4b, 0xbd, 0x8b, 0x8a,
        0x70, 0x3e, 0xb5, 0x66, 0x48, 0x03, 0xf6, 0x0e, 0x61, 0x35, 0x57, 0xb9, 0x86, 0xc1, 0x1d, 0x9e,
        0xe1, 0xf8, 0x98, 0x11, 0x69, 0xd9, 0x8e, 0x94, 0x9b, 0x1e, 0x87, 0xe9, 0xce, 0x55, 0x28, 0xdf,
        0x8c, 0xa1, 0x89, 0x0d, 0xbf, 0xe6, 0x42, 0x68, 0x41, 0x99, 0x2d, 0x0f, 0xb0, 0x54, 0xbb, 0x16
    };
    char[] mul2 = {
        0x00, 0x02, 0x04, 0x06, 0x08, 0x0a, 0x0c, 0x0e, 0x10, 0x12, 0x14, 0x16, 0x18, 0x1a, 0x1c, 0x1e,
        0x20, 0x22, 0x24, 0x26, 0x28, 0x2a, 0x2c, 0x2e, 0x30, 0x32, 0x34, 0x36, 0x38, 0x3a, 0x3c, 0x3e,
        0x40, 0x42, 0x44, 0x46, 0x48, 0x4a, 0x4c, 0x4e, 0x50, 0x52, 0x54, 0x56, 0x58, 0x5a, 0x5c, 0x5e,
        0x60, 0x62, 0x64, 0x66, 0x68, 0x6a, 0x6c, 0x6e, 0x70, 0x72, 0x74, 0x76, 0x78, 0x7a, 0x7c, 0x7e,
        0x80, 0x82, 0x84, 0x86, 0x88, 0x8a, 0x8c, 0x8e, 0x90, 0x92, 0x94, 0x96, 0x98, 0x9a, 0x9c, 0x9e,
        0xa0, 0xa2, 0xa4, 0xa6, 0xa8, 0xaa, 0xac, 0xae, 0xb0, 0xb2, 0xb4, 0xb6, 0xb8, 0xba, 0xbc, 0xbe,
        0xc0, 0xc2, 0xc4, 0xc6, 0xc8, 0xca, 0xcc, 0xce, 0xd0, 0xd2, 0xd4, 0xd6, 0xd8, 0xda, 0xdc, 0xde,
        0xe0, 0xe2, 0xe4, 0xe6, 0xe8, 0xea, 0xec, 0xee, 0xf0, 0xf2, 0xf4, 0xf6, 0xf8, 0xfa, 0xfc, 0xfe,
        0x1b, 0x19, 0x1f, 0x1d, 0x13, 0x11, 0x17, 0x15, 0x0b, 0x09, 0x0f, 0x0d, 0x03, 0x01, 0x07, 0x05,
        0x3b, 0x39, 0x3f, 0x3d, 0x33, 0x31, 0x37, 0x35, 0x2b, 0x29, 0x2f, 0x2d, 0x23, 0x21, 0x27, 0x25,
        0x5b, 0x59, 0x5f, 0x5d, 0x53, 0x51, 0x57, 0x55, 0x4b, 0x49, 0x4f, 0x4d, 0x43, 0x41, 0x47, 0x45,
        0x7b, 0x79, 0x7f, 0x7d, 0x73, 0x71, 0x77, 0x75, 0x6b, 0x69, 0x6f, 0x6d, 0x63, 0x61, 0x67, 0x65,
        0x9b, 0x99, 0x9f, 0x9d, 0x93, 0x91, 0x97, 0x95, 0x8b, 0x89, 0x8f, 0x8d, 0x83, 0x81, 0x87, 0x85,
        0xbb, 0xb9, 0xbf, 0xbd, 0xb3, 0xb1, 0xb7, 0xb5, 0xab, 0xa9, 0xaf, 0xad, 0xa3, 0xa1, 0xa7, 0xa5,
        0xdb, 0xd9, 0xdf, 0xdd, 0xd3, 0xd1, 0xd7, 0xd5, 0xcb, 0xc9, 0xcf, 0xcd, 0xc3, 0xc1, 0xc7, 0xc5,
        0xfb, 0xf9, 0xff, 0xfd, 0xf3, 0xf1, 0xf7, 0xf5, 0xeb, 0xe9, 0xef, 0xed, 0xe3, 0xe1, 0xe7, 0xe5
    };
    char[] mul3 = {
        0x00, 0x03, 0x06, 0x05, 0x0c, 0x0f, 0x0a, 0x09, 0x18, 0x1b, 0x1e, 0x1d, 0x14, 0x17, 0x12, 0x11,
        0x30, 0x33, 0x36, 0x35, 0x3c, 0x3f, 0x3a, 0x39, 0x28, 0x2b, 0x2e, 0x2d, 0x24, 0x27, 0x22, 0x21,
        0x60, 0x63, 0x66, 0x65, 0x6c, 0x6f, 0x6a, 0x69, 0x78, 0x7b, 0x7e, 0x7d, 0x74, 0x77, 0x72, 0x71,
        0x50, 0x53, 0x56, 0x55, 0x5c, 0x5f, 0x5a, 0x59, 0x48, 0x4b, 0x4e, 0x4d, 0x44, 0x47, 0x42, 0x41,
        0xc0, 0xc3, 0xc6, 0xc5, 0xcc, 0xcf, 0xca, 0xc9, 0xd8, 0xdb, 0xde, 0xdd, 0xd4, 0xd7, 0xd2, 0xd1,
        0xf0, 0xf3, 0xf6, 0xf5, 0xfc, 0xff, 0xfa, 0xf9, 0xe8, 0xeb, 0xee, 0xed, 0xe4, 0xe7, 0xe2, 0xe1,
        0xa0, 0xa3, 0xa6, 0xa5, 0xac, 0xaf, 0xaa, 0xa9, 0xb8, 0xbb, 0xbe, 0xbd, 0xb4, 0xb7, 0xb2, 0xb1,
        0x90, 0x93, 0x96, 0x95, 0x9c, 0x9f, 0x9a, 0x99, 0x88, 0x8b, 0x8e, 0x8d, 0x84, 0x87, 0x82, 0x81,
        0x9b, 0x98, 0x9d, 0x9e, 0x97, 0x94, 0x91, 0x92, 0x83, 0x80, 0x85, 0x86, 0x8f, 0x8c, 0x89, 0x8a,
        0xab, 0xa8, 0xad, 0xae, 0xa7, 0xa4, 0xa1, 0xa2, 0xb3, 0xb0, 0xb5, 0xb6, 0xbf, 0xbc, 0xb9, 0xba,
        0xfb, 0xf8, 0xfd, 0xfe, 0xf7, 0xf4, 0xf1, 0xf2, 0xe3, 0xe0, 0xe5, 0xe6, 0xef, 0xec, 0xe9, 0xea,
        0xcb, 0xc8, 0xcd, 0xce, 0xc7, 0xc4, 0xc1, 0xc2, 0xd3, 0xd0, 0xd5, 0xd6, 0xdf, 0xdc, 0xd9, 0xda,
        0x5b, 0x58, 0x5d, 0x5e, 0x57, 0x54, 0x51, 0x52, 0x43, 0x40, 0x45, 0x46, 0x4f, 0x4c, 0x49, 0x4a,
        0x6b, 0x68, 0x6d, 0x6e, 0x67, 0x64, 0x61, 0x62, 0x73, 0x70, 0x75, 0x76, 0x7f, 0x7c, 0x79, 0x7a,
        0x3b, 0x38, 0x3d, 0x3e, 0x37, 0x34, 0x31, 0x32, 0x23, 0x20, 0x25, 0x26, 0x2f, 0x2c, 0x29, 0x2a,
        0x0b, 0x08, 0x0d, 0x0e, 0x07, 0x04, 0x01, 0x02, 0x13, 0x10, 0x15, 0x16, 0x1f, 0x1c, 0x19, 0x1a
    };
    char[] rcon = {
        0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a,
        0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39,
        0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a,
        0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8,
        0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef,
        0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc,
        0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20, 0x40, 0x80, 0x1b,
        0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3,
        0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94,
        0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04, 0x08, 0x10, 0x20,
        0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63, 0xc6, 0x97, 0x35,
        0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd, 0x61, 0xc2, 0x9f,
        0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d, 0x01, 0x02, 0x04,
        0x08, 0x10, 0x20, 0x40, 0x80, 0x1b, 0x36, 0x6c, 0xd8, 0xab, 0x4d, 0x9a, 0x2f, 0x5e, 0xbc, 0x63,
        0xc6, 0x97, 0x35, 0x6a, 0xd4, 0xb3, 0x7d, 0xfa, 0xef, 0xc5, 0x91, 0x39, 0x72, 0xe4, 0xd3, 0xbd,
        0x61, 0xc2, 0x9f, 0x25, 0x4a, 0x94, 0x33, 0x66, 0xcc, 0x83, 0x1d, 0x3a, 0x74, 0xe8, 0xcb, 0x8d
    };

    void KeyExpansionCore(char[] in, int i) {
        //Rotate left
        char t = in[0];
        in[0] = in[1];
        in[1] = in[2];
        in[2] = in[3];
        in[3] = t;
        //S_box 4 bytes
        in[0] = sbox[in[0]];
        in[1] = sbox[in[1]];
        in[2] = sbox[in[2]];
        in[3] = sbox[in[3]];
        //Rcon
        in[0] ^= rcon[i];
    }

    void sboxKey256(char[] in) {
        in[0] = sbox[in[0]];
        in[1] = sbox[in[1]];
        in[2] = sbox[in[2]];
        in[3] = sbox[in[3]];
    }

    void KeyExpansion(char[] inputKey, char[] expandedKeys, int lengthKey) {
        //The first 16 bytes are the original key:

        for (int i = 0; i < (lengthKey / 8); i++) {
            expandedKeys[i] = inputKey[i];

        }
        //Variables
        int bytesGenerated = lengthKey / 8; //dem so byte tao ra
        int rconIteration = 1; //tang Rcon
        char temp[] = new char[4];//temporary storange for core
        int numberOfRounds = 0;
        switch (lengthKey) {
            case 128:
                numberOfRounds = 10;
                break;
            case 192:
                numberOfRounds = 12;
                break;
            case 256:
                numberOfRounds = 14;
                break;
        }
        int totalByte = (numberOfRounds + 1) * 16;
        while (bytesGenerated < totalByte) {//ghi 1 luc 4 byte (1 cot)            
            //4 final bytes for core
            for (int i = 0; i < 4; i++) {
                temp[i] = expandedKeys[i + bytesGenerated - 4];//lay 1 word truoc no
            }
            //Perform the core once for each 16 byte key:rotword, subyte,rcon
            if (bytesGenerated % (lengthKey / 8) == 0) {//Kiem tra da den phan 
                KeyExpansionCore(temp, rconIteration);
                rconIteration++;
            } else if (lengthKey == 256 && bytesGenerated % 16 == 0) {//=16
                sboxKey256(temp);
            }
            //Xor temp with [bytesGenerate-16], and store in expandedkey;
            for (int a = 0; a < 4; a++) {
                expandedKeys[bytesGenerated] = (char) (expandedKeys[bytesGenerated - (lengthKey / 8)] ^ temp[a]);//xor word truoc no 4 
                bytesGenerated++;
            }
        }

    }

    void InitialRound() {
    }

    void SubBytes(char[] state) {
        for (int i = 0; i < 16; i++) {
            state[i] = sbox[state[i]];
        }
    }

    void ShiftRows(char[] state) {
        char tmp[] = new char[16];
        tmp[0] = state[0];//tmp theo cot
        tmp[1] = state[5];
        tmp[2] = state[10];
        tmp[3] = state[15];

        tmp[4] = state[4];
        tmp[5] = state[9];
        tmp[6] = state[14];
        tmp[7] = state[3];

        tmp[8] = state[8];
        tmp[9] = state[13];
        tmp[10] = state[2];
        tmp[11] = state[7];

        tmp[12] = state[12];
        tmp[13] = state[1];
        tmp[14] = state[6];
        tmp[15] = state[11];
        for (int i = 0; i < 16; i++) {
            state[i] = tmp[i];
        }
    }

    void MixColumns(char[] state) {
        char[] tmp = new char[16];
        tmp[0] = (char) (mul2[state[0]] ^ mul3[state[1]] ^ state[2] ^ state[3]);
        tmp[1] = (char) (state[0] ^ mul2[state[1]] ^ mul3[state[2]] ^ state[3]);
        tmp[2] = (char) (state[0] ^ state[1] ^ mul2[state[2]] ^ mul3[state[3]]);
        tmp[3] = (char) (mul3[state[0]] ^ state[1] ^ state[2] ^ mul2[state[3]]);

        tmp[4] = (char) (mul2[state[4]] ^ mul3[state[5]] ^ state[6] ^ state[7]);
        tmp[5] = (char) (state[4] ^ mul2[state[5]] ^ mul3[state[6]] ^ state[7]);
        tmp[6] = (char) (state[4] ^ state[5] ^ mul2[state[6]] ^ mul3[state[7]]);
        tmp[7] = (char) (mul3[state[4]] ^ state[5] ^ state[6] ^ mul2[state[7]]);

        tmp[8] = (char) (mul2[state[8]] ^ mul3[state[9]] ^ state[10] ^ state[11]);
        tmp[9] = (char) (state[8] ^ mul2[state[9]] ^ mul3[state[10]] ^ state[11]);
        tmp[10] = (char) (state[8] ^ state[9] ^ mul2[state[10]] ^ mul3[state[11]]);
        tmp[11] = (char) (mul3[state[8]] ^ state[9] ^ state[10] ^ mul2[state[11]]);

        tmp[12] = (char) (mul2[state[12]] ^ mul3[state[13]] ^ state[14] ^ state[15]);
        tmp[13] = (char) (state[12] ^ mul2[state[13]] ^ mul3[state[14]] ^ state[15]);
        tmp[14] = (char) (state[12] ^ state[13] ^ mul2[state[14]] ^ mul3[state[15]]);
        tmp[15] = (char) (mul3[state[12]] ^ state[13] ^ state[14] ^ mul2[state[15]]);
        for (int i = 0; i < 16; i++) {
            state[i] = tmp[i];
        }
    }

    void AddRoundKey(char[] state, char[] roundKey) {
        for (int i = 0; i < 16; i++) {
            state[i] ^= roundKey[i];
        }
    }

    char[] AES_Encrypt(char[] message, char[] key, int lengthKey) {
        char[] state = new char[16];

        for (int i = 0; i < 16; i++) {
            state[i] = (char) message[i];
        }
        int numberOfRounds = 0;
        switch (lengthKey) {
            case 128:
                numberOfRounds = 9;
                break;
            case 192:
                numberOfRounds = 11;
                break;
            case 256:
                numberOfRounds = 13;
                break;
        }
        char[] expandedKey = new char[(numberOfRounds + 2) * 16];
        KeyExpansion(key, expandedKey, lengthKey);

        //add roundkey
        AddRoundKey(state, Arrays.copyOfRange(expandedKey, 0, 16));
        //vong lap
        for (int i = 0; i < numberOfRounds; i++) {
            SubBytes(state);
            ShiftRows(state);
            MixColumns(state);
            AddRoundKey(state, Arrays.copyOfRange(expandedKey, 16 * (i + 1), 16 * (i + 1) + 16));

        }
        //final round
        SubBytes(state);
        ShiftRows(state);
        AddRoundKey(state, Arrays.copyOfRange(expandedKey, (numberOfRounds + 1) * 16, (numberOfRounds + 2) * 16));

        for (int i = 0; i < 16; i++) {
            message[i] = state[i];
        }
        
        return message;
        
    }

    boolean enterContextKey(String enterKey) {
        char[] x = enterKey.toCharArray();
        for (int i = 0; i < enterKey.length(); i++) {
            if (x[i] < '0' | (x[i] > '9' && x[i] < 'A') | (x[i] > 'F' && x[i] < 'a') | (x[i] > 'f')) {
                return false;
            }
        }
        return true;
    }

    boolean inputKey(int lengthKey, char[] keyFromInput, String enterKey) {
        int[] keyInt = new int[(lengthKey / 8) * 2];
        int temp = 0;
        encrypt enForm = new encrypt();
        // enterKey = scanner.nextLine();
        if (enterKey.length() != (lengthKey / 8) * 2) {
            enForm.showError("Khóa nhập vào không đúng. Mời nhập lại!");
            return false;
        }
        if (enterContextKey(enterKey) == false) {
            enForm.showError("Khóa nhập vào không đúng. Mời nhập lại!");
            return false;
            //enterKey = scanner.next();
        }
        char[] key = enterKey.toCharArray();
        for (int i = 0; i < key.length; i++) {
            switch (key[i]) {
                case '0':
                    temp = 0;
                    break;
                case '1':
                    temp = 1;
                    break;
                case '2':
                    temp = 2;
                    break;
                case '3':
                    temp = 3;
                    break;
                case '4':
                    temp = 4;
                    break;
                case '5':
                    temp = 5;
                    break;
                case '6':
                    temp = 6;
                    break;
                case '7':
                    temp = 7;
                    break;
                case '8':
                    temp = 8;
                    break;
                case '9':
                    temp = 9;
                    break;

                case 'a':
                    temp = 10;
                    break;
                case 'b':
                    temp = 11;
                    break;
                case 'c':
                    temp = 12;
                    break;
                case 'd':
                    temp = 13;
                    break;
                case 'e':
                    temp = 14;
                    break;
                case 'f':
                    temp = 15;
                    break;
                case 'A':
                    temp = 10;
                    break;
                case 'B':
                    temp = 11;
                    break;
                case 'C':
                    temp = 12;
                    break;
                case 'D':
                    temp = 13;
                    break;
                case 'E':
                    temp = 14;
                    break;
                case 'F':
                    temp = 15;
                    break;
                default:
                    break;
            }
            keyInt[i] = (int) temp;
        }
        int key2[] = new int[(lengthKey / 8)];
        for (int i = 0; i < key2.length; i++) {
            for (int j = 0; j < keyInt.length; j++) {
                if (j == (2 * i)) {
                    key2[i] = keyInt[j] * 16 + keyInt[j + 1];
                }
            }
        }
        for (int i = 0; i < keyFromInput.length; i++) {
            keyFromInput[i] = (char) (key2[i]);
        }
        return true;
    }

    void printHex(char x) {
        if (x / 16 < 10) {
            System.out.print((char) ((x / 16) + '0'));
        }
        if (x / 16 >= 10) {
            System.out.print((char) ((x / 16 - 10) + 'A'));
        }

        if (x % 16 < 10) {
            System.out.print((char) ((x % 16) + '0'));
        }
        if (x % 16 >= 10) {
            System.out.print((char) ((x % 16 - 10) + 'A'));
        }
    }

    int inputKeyLength(int length) {
        if (length == 128) {
            return length;
        } else if (length == 192) {
            return length;
        } else if (length == 256) {
            return length;
        } else {
            return 0;
        }
    }

    String readFile(String path) throws IOException {
        String outputFile = "";
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
            f = new File(path);
            fr = new FileReader(f);
            //Bước 2: Đọc dữ liệu
//            br = new BufferedReader(fr);
            br = Files.newBufferedReader(f.toPath(), StandardCharsets.UTF_8);
            String line;
            while ((line = br.readLine()) != null) {
                outputFile += line;
            }
            return outputFile;
        } catch (Exception ex) {
            //System.out.println("Loi doc file: " + ex.getMessage());
            encrypt enForm = new encrypt();
            enForm.showError("File duoc lua chon doc khong dung");

        } finally {
            fr.close();
            br.close();
        }
        return outputFile;
    }
    long totalTimeDcrypt;

    void encryptAES(String plainPath, String cipherPath, int lengthKey, String enterKey) throws IOException {
        EncryptMerge aes = new EncryptMerge();
        String m1 = aes.readFile(plainPath);
        String m = m1.substring(0, m1.length());
        char[] message = m.toCharArray();

        char[] keyFromInput = new char[lengthKey / 8];
        if (aes.inputKey(lengthKey, keyFromInput, enterKey) == true) {

            int originalLen = message.length;
            int lenOfPaddedMessage = originalLen;
            if (lenOfPaddedMessage % 16 != 0) {
                lenOfPaddedMessage = (lenOfPaddedMessage / 16 + 1) * 16;// neu khong la boi cua 16 -> du -> them cac byte 0 cuoi de tao 16 byte moi
            }
            char paddedMessage[] = new char[lenOfPaddedMessage];
            for (int i = 0; i < lenOfPaddedMessage; i++) {
                if (i >= originalLen) {
                    paddedMessage[i] = 0;//them cac byte 0 cuoi de tao 16 byte moi
                } else {
                    paddedMessage[i] = message[i];
                }
            }

            //Encrypt padded message:
            char[] cipher = new char[16];
            long startTimeDecrypt = System.currentTimeMillis();
            for (int i = 0; i < lenOfPaddedMessage; i += 16) {//ma hoa 16 byte mot
                cipher = aes.AES_Encrypt(Arrays.copyOfRange(paddedMessage, i, i + 16), keyFromInput, lengthKey);
            }
           // System.out.println("\n");
            long endTimeDcrypt = System.currentTimeMillis();// kết thúc tính time
            totalTimeDcrypt = endTimeDcrypt - startTimeDecrypt;
            System.out.print("Encrypt time: " + ((totalTimeDcrypt) / 1000d) + " seconds \n");

            encrypt enForm = new encrypt();
            //writeToFile();
            try {
                FileWriter myWriter = new FileWriter(cipherPath);
                for (int i = 0; i < lenOfPaddedMessage; i += 16) {//ma hoa 16 byte mot
                    cipher = aes.AES_Encrypt(Arrays.copyOfRange(paddedMessage, i, i + 16), keyFromInput, lengthKey);
                    for (int j = 0; j < 16; j++) {
                        if (cipher[j] / 16 < 10) {
                            myWriter.append((char) ((cipher[j] / 16) + '0'));
                        }
                        if (cipher[j] / 16 >= 10) {
                            myWriter.append((char) ((cipher[j] / 16 - 10) + 'A'));
                        }

                        if (cipher[j] % 16 < 10) {
                            myWriter.append((char) ((cipher[j] % 16) + '0'));
                        }
                        if (cipher[j] % 16 >= 10) {
                            myWriter.append((char) ((cipher[j] % 16 - 10) + 'A'));
                        }
                        // myWriter.append(cipher[j]);
                    }
                }
                myWriter.close();

                enForm.showError("Ghi file ma hoa thanh cong");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                enForm.showError("File duoc lua chon ghi khong dung");
            }
        }

    }

    public static void main(String[] args) throws IOException {

    }
}
