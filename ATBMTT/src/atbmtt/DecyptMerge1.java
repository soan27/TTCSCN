/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atbmtt;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;
import com.sun.javafx.util.TempState;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author NgaDo
 */
public class DecyptMerge1 {

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

    char rsbox[]
            = {
                0x52, 0x09, 0x6a, 0xd5, 0x30, 0x36, 0xa5, 0x38, 0xbf, 0x40, 0xa3, 0x9e, 0x81, 0xf3, 0xd7, 0xfb,
                0x7c, 0xe3, 0x39, 0x82, 0x9b, 0x2f, 0xff, 0x87, 0x34, 0x8e, 0x43, 0x44, 0xc4, 0xde, 0xe9, 0xcb,
                0x54, 0x7b, 0x94, 0x32, 0xa6, 0xc2, 0x23, 0x3d, 0xee, 0x4c, 0x95, 0x0b, 0x42, 0xfa, 0xc3, 0x4e,
                0x08, 0x2e, 0xa1, 0x66, 0x28, 0xd9, 0x24, 0xb2, 0x76, 0x5b, 0xa2, 0x49, 0x6d, 0x8b, 0xd1, 0x25,
                0x72, 0xf8, 0xf6, 0x64, 0x86, 0x68, 0x98, 0x16, 0xd4, 0xa4, 0x5c, 0xcc, 0x5d, 0x65, 0xb6, 0x92,
                0x6c, 0x70, 0x48, 0x50, 0xfd, 0xed, 0xb9, 0xda, 0x5e, 0x15, 0x46, 0x57, 0xa7, 0x8d, 0x9d, 0x84,
                0x90, 0xd8, 0xab, 0x00, 0x8c, 0xbc, 0xd3, 0x0a, 0xf7, 0xe4, 0x58, 0x05, 0xb8, 0xb3, 0x45, 0x06,
                0xd0, 0x2c, 0x1e, 0x8f, 0xca, 0x3f, 0x0f, 0x02, 0xc1, 0xaf, 0xbd, 0x03, 0x01, 0x13, 0x8a, 0x6b,
                0x3a, 0x91, 0x11, 0x41, 0x4f, 0x67, 0xdc, 0xea, 0x97, 0xf2, 0xcf, 0xce, 0xf0, 0xb4, 0xe6, 0x73,
                0x96, 0xac, 0x74, 0x22, 0xe7, 0xad, 0x35, 0x85, 0xe2, 0xf9, 0x37, 0xe8, 0x1c, 0x75, 0xdf, 0x6e,
                0x47, 0xf1, 0x1a, 0x71, 0x1d, 0x29, 0xc5, 0x89, 0x6f, 0xb7, 0x62, 0x0e, 0xaa, 0x18, 0xbe, 0x1b,
                0xfc, 0x56, 0x3e, 0x4b, 0xc6, 0xd2, 0x79, 0x20, 0x9a, 0xdb, 0xc0, 0xfe, 0x78, 0xcd, 0x5a, 0xf4,
                0x1f, 0xdd, 0xa8, 0x33, 0x88, 0x07, 0xc7, 0x31, 0xb1, 0x12, 0x10, 0x59, 0x27, 0x80, 0xec, 0x5f,
                0x60, 0x51, 0x7f, 0xa9, 0x19, 0xb5, 0x4a, 0x0d, 0x2d, 0xe5, 0x7a, 0x9f, 0x93, 0xc9, 0x9c, 0xef,
                0xa0, 0xe0, 0x3b, 0x4d, 0xae, 0x2a, 0xf5, 0xb0, 0xc8, 0xeb, 0xbb, 0x3c, 0x83, 0x53, 0x99, 0x61,
                0x17, 0x2b, 0x04, 0x7e, 0xba, 0x77, 0xd6, 0x26, 0xe1, 0x69, 0x14, 0x63, 0x55, 0x21, 0x0c, 0x7d};
    char[] mul9 = {
        0x00, 0x09, 0x12, 0x1b, 0x24, 0x2d, 0x36, 0x3f, 0x48, 0x41, 0x5a, 0x53, 0x6c, 0x65, 0x7e, 0x77,
        0x90, 0x99, 0x82, 0x8b, 0xb4, 0xbd, 0xa6, 0xaf, 0xd8, 0xd1, 0xca, 0xc3, 0xfc, 0xf5, 0xee, 0xe7,
        0x3b, 0x32, 0x29, 0x20, 0x1f, 0x16, 0x0d, 0x04, 0x73, 0x7a, 0x61, 0x68, 0x57, 0x5e, 0x45, 0x4c,
        0xab, 0xa2, 0xb9, 0xb0, 0x8f, 0x86, 0x9d, 0x94, 0xe3, 0xea, 0xf1, 0xf8, 0xc7, 0xce, 0xd5, 0xdc,
        0x76, 0x7f, 0x64, 0x6d, 0x52, 0x5b, 0x40, 0x49, 0x3e, 0x37, 0x2c, 0x25, 0x1a, 0x13, 0x08, 0x01,
        0xe6, 0xef, 0xf4, 0xfd, 0xc2, 0xcb, 0xd0, 0xd9, 0xae, 0xa7, 0xbc, 0xb5, 0x8a, 0x83, 0x98, 0x91,
        0x4d, 0x44, 0x5f, 0x56, 0x69, 0x60, 0x7b, 0x72, 0x05, 0x0c, 0x17, 0x1e, 0x21, 0x28, 0x33, 0x3a,
        0xdd, 0xd4, 0xcf, 0xc6, 0xf9, 0xf0, 0xeb, 0xe2, 0x95, 0x9c, 0x87, 0x8e, 0xb1, 0xb8, 0xa3, 0xaa,
        0xec, 0xe5, 0xfe, 0xf7, 0xc8, 0xc1, 0xda, 0xd3, 0xa4, 0xad, 0xb6, 0xbf, 0x80, 0x89, 0x92, 0x9b,
        0x7c, 0x75, 0x6e, 0x67, 0x58, 0x51, 0x4a, 0x43, 0x34, 0x3d, 0x26, 0x2f, 0x10, 0x19, 0x02, 0x0b,
        0xd7, 0xde, 0xc5, 0xcc, 0xf3, 0xfa, 0xe1, 0xe8, 0x9f, 0x96, 0x8d, 0x84, 0xbb, 0xb2, 0xa9, 0xa0,
        0x47, 0x4e, 0x55, 0x5c, 0x63, 0x6a, 0x71, 0x78, 0x0f, 0x06, 0x1d, 0x14, 0x2b, 0x22, 0x39, 0x30,
        0x9a, 0x93, 0x88, 0x81, 0xbe, 0xb7, 0xac, 0xa5, 0xd2, 0xdb, 0xc0, 0xc9, 0xf6, 0xff, 0xe4, 0xed,
        0x0a, 0x03, 0x18, 0x11, 0x2e, 0x27, 0x3c, 0x35, 0x42, 0x4b, 0x50, 0x59, 0x66, 0x6f, 0x74, 0x7d,
        0xa1, 0xa8, 0xb3, 0xba, 0x85, 0x8c, 0x97, 0x9e, 0xe9, 0xe0, 0xfb, 0xf2, 0xcd, 0xc4, 0xdf, 0xd6,
        0x31, 0x38, 0x23, 0x2a, 0x15, 0x1c, 0x07, 0x0e, 0x79, 0x70, 0x6b, 0x62, 0x5d, 0x54, 0x4f, 0x46
    };
    char[] mulB = {
        0x00, 0x0b, 0x16, 0x1d, 0x2c, 0x27, 0x3a, 0x31, 0x58, 0x53, 0x4e, 0x45, 0x74, 0x7f, 0x62, 0x69,
        0xb0, 0xbb, 0xa6, 0xad, 0x9c, 0x97, 0x8a, 0x81, 0xe8, 0xe3, 0xfe, 0xf5, 0xc4, 0xcf, 0xd2, 0xd9,
        0x7b, 0x70, 0x6d, 0x66, 0x57, 0x5c, 0x41, 0x4a, 0x23, 0x28, 0x35, 0x3e, 0x0f, 0x04, 0x19, 0x12,
        0xcb, 0xc0, 0xdd, 0xd6, 0xe7, 0xec, 0xf1, 0xfa, 0x93, 0x98, 0x85, 0x8e, 0xbf, 0xb4, 0xa9, 0xa2,
        0xf6, 0xfd, 0xe0, 0xeb, 0xda, 0xd1, 0xcc, 0xc7, 0xae, 0xa5, 0xb8, 0xb3, 0x82, 0x89, 0x94, 0x9f,
        0x46, 0x4d, 0x50, 0x5b, 0x6a, 0x61, 0x7c, 0x77, 0x1e, 0x15, 0x08, 0x03, 0x32, 0x39, 0x24, 0x2f,
        0x8d, 0x86, 0x9b, 0x90, 0xa1, 0xaa, 0xb7, 0xbc, 0xd5, 0xde, 0xc3, 0xc8, 0xf9, 0xf2, 0xef, 0xe4,
        0x3d, 0x36, 0x2b, 0x20, 0x11, 0x1a, 0x07, 0x0c, 0x65, 0x6e, 0x73, 0x78, 0x49, 0x42, 0x5f, 0x54,
        0xf7, 0xfc, 0xe1, 0xea, 0xdb, 0xd0, 0xcd, 0xc6, 0xaf, 0xa4, 0xb9, 0xb2, 0x83, 0x88, 0x95, 0x9e,
        0x47, 0x4c, 0x51, 0x5a, 0x6b, 0x60, 0x7d, 0x76, 0x1f, 0x14, 0x09, 0x02, 0x33, 0x38, 0x25, 0x2e,
        0x8c, 0x87, 0x9a, 0x91, 0xa0, 0xab, 0xb6, 0xbd, 0xd4, 0xdf, 0xc2, 0xc9, 0xf8, 0xf3, 0xee, 0xe5,
        0x3c, 0x37, 0x2a, 0x21, 0x10, 0x1b, 0x06, 0x0d, 0x64, 0x6f, 0x72, 0x79, 0x48, 0x43, 0x5e, 0x55,
        0x01, 0x0a, 0x17, 0x1c, 0x2d, 0x26, 0x3b, 0x30, 0x59, 0x52, 0x4f, 0x44, 0x75, 0x7e, 0x63, 0x68,
        0xb1, 0xba, 0xa7, 0xac, 0x9d, 0x96, 0x8b, 0x80, 0xe9, 0xe2, 0xff, 0xf4, 0xc5, 0xce, 0xd3, 0xd8,
        0x7a, 0x71, 0x6c, 0x67, 0x56, 0x5d, 0x40, 0x4b, 0x22, 0x29, 0x34, 0x3f, 0x0e, 0x05, 0x18, 0x13,
        0xca, 0xc1, 0xdc, 0xd7, 0xe6, 0xed, 0xf0, 0xfb, 0x92, 0x99, 0x84, 0x8f, 0xbe, 0xb5, 0xa8, 0xa3
    };
    char[] mulD = {
        0x00, 0x0d, 0x1a, 0x17, 0x34, 0x39, 0x2e, 0x23, 0x68, 0x65, 0x72, 0x7f, 0x5c, 0x51, 0x46, 0x4b,
        0xd0, 0xdd, 0xca, 0xc7, 0xe4, 0xe9, 0xfe, 0xf3, 0xb8, 0xb5, 0xa2, 0xaf, 0x8c, 0x81, 0x96, 0x9b,
        0xbb, 0xb6, 0xa1, 0xac, 0x8f, 0x82, 0x95, 0x98, 0xd3, 0xde, 0xc9, 0xc4, 0xe7, 0xea, 0xfd, 0xf0,
        0x6b, 0x66, 0x71, 0x7c, 0x5f, 0x52, 0x45, 0x48, 0x03, 0x0e, 0x19, 0x14, 0x37, 0x3a, 0x2d, 0x20,
        0x6d, 0x60, 0x77, 0x7a, 0x59, 0x54, 0x43, 0x4e, 0x05, 0x08, 0x1f, 0x12, 0x31, 0x3c, 0x2b, 0x26,
        0xbd, 0xb0, 0xa7, 0xaa, 0x89, 0x84, 0x93, 0x9e, 0xd5, 0xd8, 0xcf, 0xc2, 0xe1, 0xec, 0xfb, 0xf6,
        0xd6, 0xdb, 0xcc, 0xc1, 0xe2, 0xef, 0xf8, 0xf5, 0xbe, 0xb3, 0xa4, 0xa9, 0x8a, 0x87, 0x90, 0x9d,
        0x06, 0x0b, 0x1c, 0x11, 0x32, 0x3f, 0x28, 0x25, 0x6e, 0x63, 0x74, 0x79, 0x5a, 0x57, 0x40, 0x4d,
        0xda, 0xd7, 0xc0, 0xcd, 0xee, 0xe3, 0xf4, 0xf9, 0xb2, 0xbf, 0xa8, 0xa5, 0x86, 0x8b, 0x9c, 0x91,
        0x0a, 0x07, 0x10, 0x1d, 0x3e, 0x33, 0x24, 0x29, 0x62, 0x6f, 0x78, 0x75, 0x56, 0x5b, 0x4c, 0x41,
        0x61, 0x6c, 0x7b, 0x76, 0x55, 0x58, 0x4f, 0x42, 0x09, 0x04, 0x13, 0x1e, 0x3d, 0x30, 0x27, 0x2a,
        0xb1, 0xbc, 0xab, 0xa6, 0x85, 0x88, 0x9f, 0x92, 0xd9, 0xd4, 0xc3, 0xce, 0xed, 0xe0, 0xf7, 0xfa,
        0xb7, 0xba, 0xad, 0xa0, 0x83, 0x8e, 0x99, 0x94, 0xdf, 0xd2, 0xc5, 0xc8, 0xeb, 0xe6, 0xf1, 0xfc,
        0x67, 0x6a, 0x7d, 0x70, 0x53, 0x5e, 0x49, 0x44, 0x0f, 0x02, 0x15, 0x18, 0x3b, 0x36, 0x21, 0x2c,
        0x0c, 0x01, 0x16, 0x1b, 0x38, 0x35, 0x22, 0x2f, 0x64, 0x69, 0x7e, 0x73, 0x50, 0x5d, 0x4a, 0x47,
        0xdc, 0xd1, 0xc6, 0xcb, 0xe8, 0xe5, 0xf2, 0xff, 0xb4, 0xb9, 0xae, 0xa3, 0x80, 0x8d, 0x9a, 0x97
    };
    char[] mulE = {
        0x00, 0x0e, 0x1c, 0x12, 0x38, 0x36, 0x24, 0x2a, 0x70, 0x7e, 0x6c, 0x62, 0x48, 0x46, 0x54, 0x5a,
        0xe0, 0xee, 0xfc, 0xf2, 0xd8, 0xd6, 0xc4, 0xca, 0x90, 0x9e, 0x8c, 0x82, 0xa8, 0xa6, 0xb4, 0xba,
        0xdb, 0xd5, 0xc7, 0xc9, 0xe3, 0xed, 0xff, 0xf1, 0xab, 0xa5, 0xb7, 0xb9, 0x93, 0x9d, 0x8f, 0x81,
        0x3b, 0x35, 0x27, 0x29, 0x03, 0x0d, 0x1f, 0x11, 0x4b, 0x45, 0x57, 0x59, 0x73, 0x7d, 0x6f, 0x61,
        0xad, 0xa3, 0xb1, 0xbf, 0x95, 0x9b, 0x89, 0x87, 0xdd, 0xd3, 0xc1, 0xcf, 0xe5, 0xeb, 0xf9, 0xf7,
        0x4d, 0x43, 0x51, 0x5f, 0x75, 0x7b, 0x69, 0x67, 0x3d, 0x33, 0x21, 0x2f, 0x05, 0x0b, 0x19, 0x17,
        0x76, 0x78, 0x6a, 0x64, 0x4e, 0x40, 0x52, 0x5c, 0x06, 0x08, 0x1a, 0x14, 0x3e, 0x30, 0x22, 0x2c,
        0x96, 0x98, 0x8a, 0x84, 0xae, 0xa0, 0xb2, 0xbc, 0xe6, 0xe8, 0xfa, 0xf4, 0xde, 0xd0, 0xc2, 0xcc,
        0x41, 0x4f, 0x5d, 0x53, 0x79, 0x77, 0x65, 0x6b, 0x31, 0x3f, 0x2d, 0x23, 0x09, 0x07, 0x15, 0x1b,
        0xa1, 0xaf, 0xbd, 0xb3, 0x99, 0x97, 0x85, 0x8b, 0xd1, 0xdf, 0xcd, 0xc3, 0xe9, 0xe7, 0xf5, 0xfb,
        0x9a, 0x94, 0x86, 0x88, 0xa2, 0xac, 0xbe, 0xb0, 0xea, 0xe4, 0xf6, 0xf8, 0xd2, 0xdc, 0xce, 0xc0,
        0x7a, 0x74, 0x66, 0x68, 0x42, 0x4c, 0x5e, 0x50, 0x0a, 0x04, 0x16, 0x18, 0x32, 0x3c, 0x2e, 0x20,
        0xec, 0xe2, 0xf0, 0xfe, 0xd4, 0xda, 0xc8, 0xc6, 0x9c, 0x92, 0x80, 0x8e, 0xa4, 0xaa, 0xb8, 0xb6,
        0x0c, 0x02, 0x10, 0x1e, 0x34, 0x3a, 0x28, 0x26, 0x7c, 0x72, 0x60, 0x6e, 0x44, 0x4a, 0x58, 0x56,
        0x37, 0x39, 0x2b, 0x25, 0x0f, 0x01, 0x13, 0x1d, 0x47, 0x49, 0x5b, 0x55, 0x7f, 0x71, 0x63, 0x6d,
        0xd7, 0xd9, 0xcb, 0xc5, 0xef, 0xe1, 0xf3, 0xfd, 0xa7, 0xa9, 0xbb, 0xb5, 0x9f, 0x91, 0x83, 0x8d
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
        while (bytesGenerated < totalByte) {//ghi 1 luc 4 word (1 cot)            
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

    void InvSubBytes(char[] state) {
        for (int i = 0; i < 16; i++) {
            state[i] = rsbox[state[i]];
        }
    }

    void InvShiftRows(char[] state) {
        char tmp[] = new char[16];
        tmp[0] = state[0];//tmp theo cot
        tmp[1] = state[13];
        tmp[2] = state[10];
        tmp[3] = state[7];

        tmp[4] = state[4];
        tmp[5] = state[1];
        tmp[6] = state[14];
        tmp[7] = state[11];

        tmp[8] = state[8];
        tmp[9] = state[5];
        tmp[10] = state[2];
        tmp[11] = state[15];

        tmp[12] = state[12];
        tmp[13] = state[9];
        tmp[14] = state[6];
        tmp[15] = state[3];
        for (int i = 0; i < 16; i++) {
            state[i] = tmp[i];
        }
    }

    void InvMixColumns(char[] state) {
        char[] tmp = new char[16];
        tmp[0] = (char) (mulE[state[0]] ^ mulB[state[1]] ^ mulD[state[2]] ^ mul9[state[3]]);

        tmp[1] = (char) (mul9[state[0]] ^ mulE[state[1]] ^ mulB[state[2]] ^ mulD[state[3]]);
        tmp[2] = (char) (mulD[state[0]] ^ mul9[state[1]] ^ mulE[state[2]] ^ mulB[state[3]]);
        tmp[3] = (char) (mulB[state[0]] ^ mulD[state[1]] ^ mul9[state[2]] ^ mulE[state[3]]);

        tmp[4] = (char) (mulE[state[4]] ^ mulB[state[5]] ^ mulD[state[6]] ^ mul9[state[7]]);
        tmp[5] = (char) (mul9[state[4]] ^ mulE[state[5]] ^ mulB[state[6]] ^ mulD[state[7]]);
        tmp[6] = (char) (mulD[state[4]] ^ mul9[state[5]] ^ mulE[state[6]] ^ mulB[state[7]]);
        tmp[7] = (char) (mulB[state[4]] ^ mulD[state[5]] ^ mul9[state[6]] ^ mulE[state[7]]);

        tmp[8] = (char) (mulE[state[8]] ^ mulB[state[9]] ^ mulD[state[10]] ^ mul9[state[11]]);
        tmp[9] = (char) (mul9[state[8]] ^ mulE[state[9]] ^ mulB[state[10]] ^ mulD[state[11]]);
        tmp[10] = (char) (mulD[state[8]] ^ mul9[state[9]] ^ mulE[state[10]] ^ mulB[state[11]]);
        tmp[11] = (char) (mulB[state[8]] ^ mulD[state[9]] ^ mul9[state[10]] ^ mulE[state[11]]);

        tmp[12] = (char) (mulE[state[12]] ^ mulB[state[13]] ^ mulD[state[14]] ^ mul9[state[15]]);
        tmp[13] = (char) (mul9[state[12]] ^ mulE[state[13]] ^ mulB[state[14]] ^ mulD[state[15]]);
        tmp[14] = (char) (mulD[state[12]] ^ mul9[state[13]] ^ mulE[state[14]] ^ mulB[state[15]]);
        tmp[15] = (char) (mulB[state[12]] ^ mulD[state[13]] ^ mul9[state[14]] ^ mulE[state[15]]);
        for (int i = 0; i < 16; i++) {
            state[i] = tmp[i];
        }
    }

    void AddRoundKey(char[] state, char[] roundKey) {
        for (int i = 0; i < 16; i++) {
            state[i] ^= roundKey[i];
        }
    }

    char[] AES_Decrypt(char[] cipher, char[] key, int lengthKey) {
        char[] state = new char[16];
        for (int i = 0; i < 16; i++) {
            state[i] = (char) cipher[i];
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
//        System.out.println("So vong :" + numberOfRounds);
        char[] expandedKey = new char[(numberOfRounds + 2) * 16];
        KeyExpansion(key, expandedKey, lengthKey);
        //add roundkey
        AddRoundKey(state, Arrays.copyOfRange(expandedKey, (numberOfRounds + 1) * 16, (numberOfRounds + 2) * 16));
        for (int i = 0; i < numberOfRounds; i++) {
            InvShiftRows(state);
            InvSubBytes(state);
            AddRoundKey(state, Arrays.copyOfRange(expandedKey, 16 * (numberOfRounds - i), 16 * (numberOfRounds - i) + 16));
            InvMixColumns(state);
        }
        //final round
        InvShiftRows(state);
        InvSubBytes(state);
        AddRoundKey(state, Arrays.copyOfRange(expandedKey, 0, 16));
        for (int i = 0; i < 16; i++) {
            cipher[i] = state[i];
        }
        return cipher;
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

    boolean enterContextKey(String enterKey) {
        char[] x = enterKey.toCharArray();
        for (int i = 0; i < enterKey.length(); i++) {
            if (x[i] < '0' | (x[i] > '9' && x[i] < 'A') | (x[i] > 'F' && x[i] < 'a') | (x[i] > 'f')) {
                return false;
            }
        }
        return true;
    }


    void convertInt(char[] key, int[] keyInt, char[] keyFromInput) {
        int temp = 0;
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
        int key2[] = new int[(key.length) / 2];
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
    }

    boolean inputKey(int lengthKey, char[] keyFromInput, String enterKey) {
      
        int[] keyInt = new int[(lengthKey / 8) * 2];
        int temp = 0;
       encrypt enForm = new encrypt();
        if (enterKey.length() != (lengthKey / 8) * 2) {
            enForm.showError("Khóa nhập vào không đúng. Mời nhập lại!");
            return false;
        }
        if (enterContextKey(enterKey) == false) {
            enForm.showError("Khóa nhập vào không đúng. Mời nhập lại!");
            return false;
        }
        char[] key = enterKey.toCharArray();
        convertInt(key, keyInt, keyFromInput);
        return true;
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

    String readFile(String cipherPath) throws IOException {
        String outputFile = "";
        File f = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            //Bước 1: Tạo đối tượng luồng và liên kết nguồn dữ liệu
            f = new File(cipherPath);
            fr = new FileReader(f);
            //Bước 2: Đọc dữ liệu
            br = Files.newBufferedReader(f.toPath(), StandardCharsets.UTF_8);

            String line;
            while ((line = br.readLine()) != null) {
                outputFile += line;
            }
            return outputFile;
        } catch (Exception ex) {
            encrypt enForm = new encrypt();
            enForm.showError("Loi doc file");

        } finally {
            fr.close();
            br.close();
        }
        return outputFile;
    }
long totalTimeDcrypt;
    void decryptAES(String cipherPath, String plainPath, int lengthKey, String enterKey) throws IOException {
        DecyptMerge1 aes = new DecyptMerge1();
        String m = aes.readFile(cipherPath);
        String m1 = m.substring(0, m.length());
        char[] cipherInput = m1.toCharArray();
        int[] cipherInt = new int[m1.length()];
        char[] cipher = new char[m1.length() / 2];
        aes.convertInt(cipherInput, cipherInt, cipher);

        //key 16 byte
        char[] keyFromInput = new char[lengthKey / 8];
       if (aes.inputKey(lengthKey, keyFromInput, enterKey) == true) {
        int originalLen = cipher.length;
        int lenOfPaddedCipher = originalLen;
        if (lenOfPaddedCipher % 16 != 0) {
            lenOfPaddedCipher = (lenOfPaddedCipher / 16 + 1) * 16;// neu khong la boi cua 16 -> du -> them cac byte 0 cuoi de tao 16 byte moi
        }
        char paddedMessage[] = new char[lenOfPaddedCipher];
        for (int i = 0; i < lenOfPaddedCipher; i++) {
            if (i >= originalLen) {
                paddedMessage[i] = 0;//them cac byte 0 cuoi de tao 16 byte moi
            } else {
                paddedMessage[i] = cipher[i];
            }
        }
        //Encrypt padded message:
        long startTimeDecrypt = System.currentTimeMillis();
        for (int i = 0; i < cipher.length; i += 16) {//ma hoa 16 byte mot
            aes.AES_Decrypt(Arrays.copyOfRange(cipher, i, i + 16), keyFromInput, lengthKey);
        }
        long endTimeDcrypt = System.currentTimeMillis();// kết thúc tính time
        totalTimeDcrypt = endTimeDcrypt - startTimeDecrypt;
        //System.out.print("Decrypt time: " + ((totalTimeDcrypt) / 1000d) + " seconds \n");
        encrypt enForm = new encrypt();
        char[] plain = new char[16];
        try {
            FileWriter myWriter = new FileWriter(plainPath);
            for (int i = 0; i < lenOfPaddedCipher; i += 16) {//ma hoa 16 byte mot
                plain = aes.AES_Decrypt(Arrays.copyOfRange(cipher, i, i + 16), keyFromInput, lengthKey);
                for (int j = 0; j < 16; j++) {
                    myWriter.append(plain[j]);
                }
            }
            myWriter.close();
             enForm.showError("Ghi file giai ma thanh cong");
        } catch (IOException e) {
            enForm.showError("Loi ghi file");
            e.printStackTrace();
        }
       }

    }

    public static void main(String[] args) throws IOException {
   
    }
}
