package com.apparatus.testcases.utils;

import com.apparatus.utils.StringUtil;
import com.devamatre.logger.LogManager;

public class TestStringUtil
{
	private static boolean EXEC_TEST_CASEA = false;
	
	public static void setExecuteTestCases(boolean executeTestCases)
	{
		TestStringUtil.EXEC_TEST_CASEA = executeTestCases;
	}
	
	/* Main Method */
	public static void main(String[] args) {
		LogManager.configure(LogManager.LOG4J_PROPERTY_FILE);
		
		if(TestStringUtil.EXEC_TEST_CASEA)
		{

			System.out.println("EMPTY_STR:" + StringUtil.EMPTY_STR + ", SPACE_SEP:"
					+ StringUtil.STR_SPACE);
			System.out
					.println("EMPTY_STR == SPACE_STR:" + (StringUtil.EMPTY_STR == StringUtil.STR_SPACE));
			System.out.println("EMPTY_STR.equals(SPACE_SEP):"
					+ StringUtil.EMPTY_STR.equals(StringUtil.STR_SPACE));
	
			System.out.println();
			String str = "   System     Idle Process    1220 Console   ";
			System.out.println("str :" + str);
			System.out.println("ltrim(" + str + "):" + StringUtil.lTrim(str));
			System.out.println("rTrim(" + str + "):" + StringUtil.rTrim(str));
			System.out.println("iTrim(" + str + "):" + StringUtil.iTrim(str));
			System.out.println("trim(" + str + "):" + StringUtil.trim(str));
	
			System.out.println();
			str = "Rohtash?? S??ingh?";
			String delimiter = "??";
			System.out.println("trim(" + str + ", " + delimiter + "):"
					+ StringUtil.trim(str, delimiter));
	
			System.out.println();
			str = "Rohtash Singh";
			System.out.println("hasSpace(" + str + "):" + StringUtil.hasSpace(str));
	
			System.out.println();
			str = "16011975";
			System.out.println("isNumeric(" + str + "):"
					+ StringUtil.isNumeric(str));
			str = "16011975.25";
			System.out.println("isNumeric(" + str + "):"
					+ StringUtil.isNumeric(str));
			str = "16Jan1975";
			System.out.println("isNumeric(" + str + "):"
					+ StringUtil.isNumeric(str));
	
			System.out.println();
			str = "Rohtash Singh - Fantastic";
			String find = "h";
			String with = "HH";
			System.out.println("replace(" + str + ", " + find + ", " + with + "):"
					+ StringUtil.replace(str, find, with));
	
			find = "ta";
			with = "W";
			System.out.println("replace(" + str + ", " + find + ", " + with + "):"
					+ StringUtil.replace(str, find, with));
	
			System.out.println();
			String email = "rohtash.singh@gmail.com";
			System.out.println("isValidEmail(" + email + "): "
					+ StringUtil.isValidEmail(email));
	
			System.out.println();
			str = "yes";
			System.out.println("isTrueOrYes(" + str + "): "
					+ StringUtil.isTrueOrYes(str));
			str = "True";
			System.out.println("isTrueOrYes(" + str + "): "
					+ StringUtil.isTrueOrYes(str));
	
			System.out.println();
			str = null;
			System.out.println("isNullOrEmpty(" + str + "): "
					+ StringUtil.isNullOrEmpty(str));
			str = "";
			System.out.println("isNullOrEmpty(" + str + "): "
					+ StringUtil.isNullOrEmpty(str));
			str = "Not Null";
			System.out.println("isNullOrEmpty(" + str + "): "
					+ StringUtil.isNullOrEmpty(str));
	
			System.out.println();
			str = "Devamatre";
			int repeat = 10;
			String padStr = "*";
			System.out.println("padLeft(" + str + ", " + repeat + ", " + padStr
					+ "):" + StringUtil.padLeft(str, repeat, padStr));
			System.out.println("padRight(" + str + ", " + repeat + ", " + padStr
					+ "):" + StringUtil.padRight(str, repeat, padStr));
			repeat = 11;
			System.out.println("padCenter(" + str + ", " + repeat + ", " + padStr
					+ "):" + StringUtil.padCenter(str, repeat, padStr));
	
			System.out.println();
			str = "is it in sentence case?";
			System.out.println("toSentenceCase(" + str + "): "
					+ StringUtil.toSentenceCase(str));
			System.out.println("toTitleCase(" + str + "): "
					+ StringUtil.toTitleCase(str));
			str = "is iT in Toggle cAse?";
			System.out.println("toToggleCase(" + str + "): "
					+ StringUtil.toToggleCase(str));
	
			System.out.println();
			String i18n = "internationalization";
			String i10n = "localization";
			System.out.println(i18n + ":" + i18n.length() + ", " + i10n + ":"
					+ i10n.length());
	
			System.out.println();
			String strTab = "this is" + StringUtil.HTAB + " horizontal tab.";
			System.out.println("HTAB: " + strTab);
			System.out.println("VTAB: " + StringUtil.VTAB);
	
			System.out.println();
			String[] elements = { "the", "quick", "brown", "fox", "jumps", "over",
					"the", "little", "lazy", "dog." };
			StringUtil.printLine(elements);
			String sentence = StringUtil.join(elements, null);
			System.out.println("join(" + elements + ", null" + "): " + sentence);
			sentence = StringUtil.join(elements, "*");
			System.out.println("join(" + elements + ", *" + "): " + sentence);
			sentence = StringUtil.join(elements);
			System.out.println("sentence: " + sentence);
	
			System.out.println();
			String[] words = StringUtil.split(sentence);
			System.out.println("split(" + sentence + "):" + StringUtil.join(words));
	
			sentence = StringUtil.join(elements, "*");
			System.out.println("sentence: " + sentence);
			words = StringUtil.split(sentence, "*");
			System.out.println("split(" + sentence + ", *): "
					+ StringUtil.join(words));
	
			sentence = "Splitted using     the space:";
			words = StringUtil.split(sentence, StringUtil.SPACE);
			System.out.println("split(" + sentence + ", ' ') => "
					+ StringUtil.join(words));
			System.out.println("Splitted using the space:");
			StringUtil.printLine(words);
	
			sentence = "This contains a \n (new line) character.";
			words = StringUtil.split(sentence, '\n');
			System.out.println("split(" + sentence + ", '\\n') => "
					+ StringUtil.join(words));
			System.out.println("Splitted using the \\n (newline) character:");
			StringUtil.printLine(words);
	
			System.out.println();
			str = null;
			int number = 0;
			char cChat = '*';
			str = StringUtil.fill(cChat, number);
			System.out.println("fill(" + cChat + ", " + number + ") -->" + str);
			number = 5;
			str = StringUtil.fill(cChat, number);
			System.out.println("fill(" + cChat + ", " + number + ") -->" + str);
	
			number = 5;
			str = StringUtil.fill(number);
			System.out.println("fill(" + number + ") -->" + str);
	
			System.out.println();
			sentence = StringUtil.join(elements);
			System.out.println("countWords(" + sentence + "): "
					+ StringUtil.countWords(sentence));
	
			System.out.println();
			sentence = StringUtil.join(elements);
			sentence += " The dog was so lazy The!";
			words = StringUtil.split(sentence);
			System.out.println("sentence:" + sentence);
			System.out.println("Unique Words (ignoreCase:false) => " + StringUtil.findUnique(words, false));
			System.out.println("Unique Words (ignoreCase:true) => " + StringUtil.findUnique(words, true));
			System.out.println("Unique Words Default (ignoreCase:false) => " + StringUtil.findUnique(words));
	
			System.out.println("Duplicate Words (ignoreCase:false) => " + StringUtil.findDuplicate(words, false));
			System.out.println("Duplicate Words (ignoreCase:true) => " + StringUtil.findDuplicate(words, true));
			System.out.println("Duplicate Words Default (ignoreCase:false) => " + StringUtil.findDuplicate(words));
		}
		
		// System.out.println();
		// char padChar = '*';
		// System.out.println("repeat(" + str + ", " + repeat + ", " + padStr
		// + ", " + LocationEnum.LEFT + "):"
		// + StringUtil.repeat(str, repeat, padChar, LocationEnum.LEFT));
		// System.out.println("repeat(" + str + ", " + repeat + ", " + padStr
		// + ", " + LocationEnum.RIGHT + "):"
		// + StringUtil.repeat(str, repeat, padChar, LocationEnum.RIGHT));
		// repeat = 13;
		// System.out.println("repeat(" + str + ", " + repeat + ", " + padStr
		// + ", " + LocationEnum.CENTER + "):"
		// + StringUtil.repeat(str, repeat, padChar, LocationEnum.CENTER));
		//
		// System.out.println();
		// str = "Rohtash Singh";
		// int start = 3, end = 7;
		// System.out.println("truncate(" + str + ", " + start + ", " + end
		// + "): " + StringUtil.truncate(str, start, end));
		//
		// System.out.println();
		// repeat = 5;
		// System.out.println("truncate(" + str + ", " + repeat + ", "
		// + LocationEnum.LEFT + "):"
		// + StringUtil.truncate(str, repeat, LocationEnum.LEFT));
		// System.out.println("truncate(" + str + ", " + repeat + ", "
		// + LocationEnum.RIGHT + "):"
		// + StringUtil.truncate(str, repeat, LocationEnum.RIGHT));
		/* commented after testing */
		// System.out.println("truncate(" + str + ", " + repeat + ", "
		// + LocationEnum.CENTER + "):"
		// + StringUtil.truncate(str, repeat, LocationEnum.CENTER));

		System.out.println();
		StringUtil.printWords(16011975, false);
	}
}
