package com.planttech.util;

import org.apache.commons.lang3.StringEscapeUtils;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;

@SuppressWarnings("deprecation")
public class HTMLCharacterEscapes extends CharacterEscapes {
	  private static final long serialVersionUID = 1L;
	  private final int[] asciiEscapes;

	  public HTMLCharacterEscapes() {
	    //XSS 방지 특수 문자 지정
	    asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
	    asciiEscapes['<'] = CharacterEscapes.ESCAPE_CUSTOM;
	    asciiEscapes['>'] = CharacterEscapes.ESCAPE_CUSTOM;
	    // 쌍따옴표 따옴표
	    asciiEscapes['\"'] = CharacterEscapes.ESCAPE_CUSTOM;
	    asciiEscapes['\''] = CharacterEscapes.ESCAPE_CUSTOM;
	    asciiEscapes['('] = CharacterEscapes.ESCAPE_CUSTOM;
	    asciiEscapes[')'] = CharacterEscapes.ESCAPE_CUSTOM;
	    asciiEscapes['&'] = CharacterEscapes.ESCAPE_CUSTOM;
	    asciiEscapes['#'] = CharacterEscapes.ESCAPE_CUSTOM;

	  }
	  
	  @Override
	  public SerializableString getEscapeSequence(int ch) {
		//Escape 처리
	    char charAt = (char) ch;
	    if (Character.isHighSurrogate(charAt) || Character.isLowSurrogate(charAt)) {
	    	// 이모지 처리
	      StringBuilder sb = new StringBuilder();
	      sb.append("\\u");
	      sb.append(String.format("%04x", ch));
	      return new SerializedString(sb.toString());
	    } else {
	      return new SerializedString(StringEscapeUtils.escapeHtml4(Character.toString(charAt)));
	    }
	  }

	  @Override
	  public int[] getEscapeCodesForAscii() {
	    return asciiEscapes;
	  }

	 
}