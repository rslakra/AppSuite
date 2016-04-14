package com.apparatus.testcases.utils;

import com.apparatus.utils.StringHelper;
import com.devmatre.logger.LogManager;

public class TestStringHelper {
	private static boolean EXEC_TEST_CASEA = false;
	
	public static void setExecuteTestCases(boolean executeTestCases) {
		TestStringHelper.EXEC_TEST_CASEA = executeTestCases;
	}
	
	/* Main Method */
	public static void main(String[] args) {
		LogManager.configure(LogManager.LOG4J_PROPERTY_FILE);
		
		if(TestStringHelper.EXEC_TEST_CASEA) {
			
			System.out.println("EMPTY_STR:" + StringHelper.EMPTY_STR + ", SPACE_SEP:" + StringHelper.STR_SPACE);
			System.out.println("EMPTY_STR == SPACE_STR:" + (StringHelper.EMPTY_STR == StringHelper.STR_SPACE));
			System.out.println("EMPTY_STR.equals(SPACE_SEP):" + StringHelper.EMPTY_STR.equals(StringHelper.STR_SPACE));
			
			System.out.println();
			String str = "   System     Idle Process    1220 Console   ";
			System.out.println("str :" + str);
			System.out.println("ltrim(" + str + "):" + StringHelper.lTrim(str));
			System.out.println("rTrim(" + str + "):" + StringHelper.rTrim(str));
			System.out.println("iTrim(" + str + "):" + StringHelper.iTrim(str));
			System.out.println("trim(" + str + "):" + StringHelper.trim(str));
			
			System.out.println();
			str = "Rohtash?? S??ingh?";
			String delimiter = "??";
			System.out.println("trim(" + str + ", " + delimiter + "):" + StringHelper.trim(str, delimiter));
			
			System.out.println();
			str = "Rohtash Singh";
			System.out.println("hasSpace(" + str + "):" + StringHelper.hasSpace(str));
			
			System.out.println();
			str = "16011975";
			System.out.println("isNumeric(" + str + "):" + StringHelper.isNumeric(str));
			str = "16011975.25";
			System.out.println("isNumeric(" + str + "):" + StringHelper.isNumeric(str));
			str = "16Jan1975";
			System.out.println("isNumeric(" + str + "):" + StringHelper.isNumeric(str));
			
			System.out.println();
			str = "Rohtash Singh - Fantastic";
			String find = "h";
			String with = "HH";
			System.out.println("replace(" + str + ", " + find + ", " + with + "):" + StringHelper.replace(str, find, with));
			
			find = "ta";
			with = "W";
			System.out.println("replace(" + str + ", " + find + ", " + with + "):" + StringHelper.replace(str, find, with));
			
			System.out.println();
			String email = "rohtash.singh@gmail.com";
			System.out.println("isValidEmail(" + email + "): " + StringHelper.isValidEmail(email));
			
			System.out.println();
			str = "yes";
			System.out.println("isTrueOrYes(" + str + "): " + StringHelper.isTrueOrYes(str));
			str = "True";
			System.out.println("isTrueOrYes(" + str + "): " + StringHelper.isTrueOrYes(str));
			
			System.out.println();
			str = null;
			System.out.println("isNullOrEmpty(" + str + "): " + StringHelper.isNullOrEmpty(str));
			str = "";
			System.out.println("isNullOrEmpty(" + str + "): " + StringHelper.isNullOrEmpty(str));
			str = "Not Null";
			System.out.println("isNullOrEmpty(" + str + "): " + StringHelper.isNullOrEmpty(str));
			
			System.out.println();
			str = "Devamatre";
			int repeat = 10;
			String padStr = "*";
			System.out.println("padLeft(" + str + ", " + repeat + ", " + padStr + "):" + StringHelper.padLeft(str, repeat, padStr));
			System.out.println("padRight(" + str + ", " + repeat + ", " + padStr + "):" + StringHelper.padRight(str, repeat, padStr));
			repeat = 11;
			System.out.println("padCenter(" + str + ", " + repeat + ", " + padStr + "):" + StringHelper.padCenter(str, repeat, padStr));
			
			System.out.println();
			str = "is it in sentence case?";
			System.out.println("toSentenceCase(" + str + "): " + StringHelper.toSentenceCase(str));
			System.out.println("toTitleCase(" + str + "): " + StringHelper.toTitleCase(str));
			str = "is iT in Toggle cAse?";
			System.out.println("toToggleCase(" + str + "): " + StringHelper.toToggleCase(str));
			
			System.out.println();
			String i18n = "internationalization";
			String i10n = "localization";
			System.out.println(i18n + ":" + i18n.length() + ", " + i10n + ":" + i10n.length());
			
			System.out.println();
			String strTab = "this is" + StringHelper.HTAB + " horizontal tab.";
			System.out.println("HTAB: " + strTab);
			System.out.println("VTAB: " + StringHelper.VTAB);
			
			System.out.println();
			String[] elements = { "the", "quick", "brown", "fox", "jumps", "over", "the", "little", "lazy", "dog."};
			StringHelper.printLine(elements);
			String sentence = StringHelper.join(elements, null);
			System.out.println("join(" + elements + ", null" + "): " + sentence);
			sentence = StringHelper.join(elements, "*");
			System.out.println("join(" + elements + ", *" + "): " + sentence);
			sentence = StringHelper.join(elements);
			System.out.println("sentence: " + sentence);
			
			System.out.println();
			String[] words = StringHelper.split(sentence);
			System.out.println("split(" + sentence + "):" + StringHelper.join(words));
			
			sentence = StringHelper.join(elements, "*");
			System.out.println("sentence: " + sentence);
			words = StringHelper.split(sentence, "*");
			System.out.println("split(" + sentence + ", *): " + StringHelper.join(words));
			
			sentence = "Splitted using     the space:";
			words = StringHelper.split(sentence, StringHelper.SPACE);
			System.out.println("split(" + sentence + ", ' ') => " + StringHelper.join(words));
			System.out.println("Splitted using the space:");
			StringHelper.printLine(words);
			
			sentence = "This contains a \n (new line) character.";
			words = StringHelper.split(sentence, '\n');
			System.out.println("split(" + sentence + ", '\\n') => " + StringHelper.join(words));
			System.out.println("Splitted using the \\n (newline) character:");
			StringHelper.printLine(words);
			
			System.out.println();
			str = null;
			int number = 0;
			char cChat = '*';
			str = StringHelper.fill(cChat, number);
			System.out.println("fill(" + cChat + ", " + number + ") -->" + str);
			number = 5;
			str = StringHelper.fill(cChat, number);
			System.out.println("fill(" + cChat + ", " + number + ") -->" + str);
			
			number = 5;
			str = StringHelper.fill(number);
			System.out.println("fill(" + number + ") -->" + str);
			
			System.out.println();
			sentence = StringHelper.join(elements);
			System.out.println("countWords(" + sentence + "): " + StringHelper.countWords(sentence));
			
			System.out.println();
			sentence = StringHelper.join(elements);
			sentence += " The dog was so lazy The!";
			words = StringHelper.split(sentence);
			System.out.println("sentence:" + sentence);
			System.out.println("Unique Words (ignoreCase:false) => " + StringHelper.findUnique(words, false));
			System.out.println("Unique Words (ignoreCase:true) => " + StringHelper.findUnique(words, true));
			System.out.println("Unique Words Default (ignoreCase:false) => " + StringHelper.findUnique(words));
			
			System.out.println("Duplicate Words (ignoreCase:false) => " + StringHelper.findDuplicate(words, false));
			System.out.println("Duplicate Words (ignoreCase:true) => " + StringHelper.findDuplicate(words, true));
			System.out.println("Duplicate Words Default (ignoreCase:false) => " + StringHelper.findDuplicate(words));
		}
		
		// System.out.println();
		// char padChar = '*';
		// System.out.println("repeat(" + str + ", " + repeat + ", " + padStr
		// + ", " + LocationEnum.LEFT + "):"
		// + StringHelper.repeat(str, repeat, padChar, LocationEnum.LEFT));
		// System.out.println("repeat(" + str + ", " + repeat + ", " + padStr
		// + ", " + LocationEnum.RIGHT + "):"
		// + StringHelper.repeat(str, repeat, padChar, LocationEnum.RIGHT));
		// repeat = 13;
		// System.out.println("repeat(" + str + ", " + repeat + ", " + padStr
		// + ", " + LocationEnum.CENTER + "):"
		// + StringHelper.repeat(str, repeat, padChar, LocationEnum.CENTER));
		//
		// System.out.println();
		// str = "Rohtash Singh";
		// int start = 3, end = 7;
		// System.out.println("truncate(" + str + ", " + start + ", " + end
		// + "): " + StringHelper.truncate(str, start, end));
		//
		// System.out.println();
		// repeat = 5;
		// System.out.println("truncate(" + str + ", " + repeat + ", "
		// + LocationEnum.LEFT + "):"
		// + StringHelper.truncate(str, repeat, LocationEnum.LEFT));
		// System.out.println("truncate(" + str + ", " + repeat + ", "
		// + LocationEnum.RIGHT + "):"
		// + StringHelper.truncate(str, repeat, LocationEnum.RIGHT));
		/* commented after testing */
		// System.out.println("truncate(" + str + ", " + repeat + ", "
		// + LocationEnum.CENTER + "):"
		// + StringHelper.truncate(str, repeat, LocationEnum.CENTER));
		
		System.out.println();
		StringHelper.printWords(16011975, false);
	}
}
