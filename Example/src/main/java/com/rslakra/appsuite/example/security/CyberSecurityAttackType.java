package com.rslakra.appsuite.example.security;

/**
 * @author Rohtash Lakra
 * @created 8/14/23 11:37 AM
 */
public enum CyberSecurityAttackType {


    PHISHING("Phishing",
             "Phishing is a form of social engineering and scam where attackers deceive people into revealing sensitive information or installing malware such as ransomware."),
    MALWARE("Malware",
            "Malware is any software intentionally designed to cause disruption to a computer, server, client, or computer network, leak private information, gain unauthorized access to information or systems, deprive access to information, or which unknowingly interferes with the user's computer security and privacy. "),
    RANSOMWARE("Ransomware",
               "Ransomware is a type of malware from cryptovirology that threatens to publish the victim's personal data or permanently block access to it unless a ransom is paid off. While some simple ransomware may lock the system without damaging any files, more advanced malware uses a technique called cryptoviral extortion."),
    DENIAL_OF_SERVICE_ATTACK("Denial-of-service attack",
                             "In computing, a denial-of-service attack is a cyber-attack in which the perpetrator seeks to make a machine or network resource unavailable to its intended users by temporarily or indefinitely disrupting services of a host connected to a network."),
    MAN_IN_THE_MIDDLE_ATTACK("Man-in-the-middle attack",
                             "In cryptography and computer security, a man-in-the-middle attack is a cyberattack where the attacker secretly relays and possibly alters the communications between two parties who believe that they are directly communicating with each other, as the attacker has inserted themselves between the two parties."),
    SQL_INJECTION("SQL injection",
                  "In computing, SQL injection is a code injection technique used to attack data-driven applications, in which malicious SQL statements are inserted into an entry field for execution."),
    Phishing("Social engineering",
             "In the context of information security, social engineering is the psychological manipulation of people into performing actions or divulging confidential information."),
//    Phishing("Phishing", "Phishing"),
    ;

    private String type;
    private String description;

    CyberSecurityAttackType(String type, String description) {
        this.type = type;
        this.description = description;
    }
}
