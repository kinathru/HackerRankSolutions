package com.hackerrank.tagcontentextractor;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;


public class TagContentExtractor {
    public static void main(String[] args) {

        String [] inputLines = new String[]{
                "<h1>Nayeem loves counseling</h1>",
                "<h1><h1>Sanjay has no watch</h1></h1><par>So wait for a while</par>",
                "<Amee>safat codes like a ninja</amee>",
                "<SA premium>Imtiaz has a secret crush</SA premium>",
                "<a>contents</a>invalid</h1>",
                "<SA premium>Imtiaz has a secret crush</SA premium><h1></h1>",
                "<div><tag1></div>",

                "<h1>some</h1>",
                "<h1>had<h1>public</h1></h1>",
                "<h1>had<h1>public</h1515></h1>",
                "<h1><h1></h1></h1>",
                "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<",
                ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>",
                "<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>>>><<<<<<<<<<<<<<<<<<<<<<<<<<<>>>>>>>>>>>>>>>>>>>>>>>",
                "<>hello</>",
                "<>hello</><h>dim</h>",
                "<>hello</><h>dim</h>>>>>",

                "qqoNVOmJDG@6IBDZoEmk9337LswEL&TQnLCuR`04XD%1t{G)Jmi_iNEXKwp&<iBKMbDGtF4v@coLsF1_LqgTJ3cSp& 3a~I&Q(j0h_w~Vk(oBZCL#vhYY9%c><wkjkTvAuA8Zk}n_l7Si\"-pfm`M8YE3F}4`YQyChgC3uRbyTvu>bMUGux)5n7L={M}e^`0xlSm5ce}ehiE}CJ6y0KPd~~B~ak5$PTdPGv}QnXpw6n9V8wVCVaTRTgLKkeF</wkjkTvAuA8Zk}n_l7Si\"-pfm`M8YE3F}4`YQyChgC3uRbyTvu>haZQKlWPxlRqXXkKHo=FDofc6$_S-GWA&m0zT*D~uorf_nAF^ym*U&6hGAI)s<XshvNhnnNbeVDuxRcQAgTpWZ-kqIps-@@}Uwq0J3Z06Y5mZgB9><FbTSC#F104{py9Xl6s{yi-R~}k5Fv4i1kCgmBY7P=vVj-j48xUg8x9BCxl~Y><lyxRRMqnMBGj1_d7Qqh5Ebn7 aMb{Q0Dm){9~I0DTS8BZ7+bui~)rQ\"2Yb4f>EeZWvJvHIk</XshvNhnnNbeVDuxRcQAgTpWZ-kqIps-@@}Uwq0J3Z06Y5mZgB9><BkkZV631Pnj}#%TWhZn@Y><kXjDpTvLA^tnXYb`h+cA J2\n" +
                        "wRhDGQCG`r N4=cWqp4jF\"#I7#)jofKUYktmjH(\"s2nD4+NxsN)tpCf2U@78MsGNbEgSgR=6`\"y685~I(D-p&T2BnaJNa%S%y9pYMb_9v{PlScQ7R=~~xnSkpSd<orcsjzalN#eslhhH2d\"1MD)pzc*$tBG-mmI_*zW><xMphtS-F8MVM^u2n5tN2DthGw{KZ`y7)><ZpjQU2&UMjzwJwnV70-CiM}M-OvR%a)Vk3R}2><DqcnlvJJa7ZYYrX^M*BI^*A_-mm0 HT4i^syNtujpEE8M@><mpX{FWXe#$d`)QdqT#eBbH=EJOXc@cFhxpJg14#*G0 o-w9G++`GA9U><PbwFCKiY2D^jhX_l7fM}tsB6QKv#@U#g%P%><WNbHpmOM# X_$FjjX$49DedlEf~_d&3j`LU9OLM%@y)2u^A5aO#><gXzyXZFrpOGaC+M4)kG`d5L*lRW5A#O0i=V023f_iSwqL$_qRMp59PW gf6><GfYTkjn{CTWM-U@3Wv(h( ~Cnc9&BKA>YjncQJp=mN8OV9WEviqvJT`YS$WD=^gryB)NWAal</GfYTkjn{CTWM-U@3Wv(h( ~Cnc9&BKA>QMewBnIzsV</WNbHpmOM# X_$FjjX$49DedlEf~_d&3j`LU9OLM%@y)2u^A5aO#></PbwFCKiY2D^jhX_l7fM}tsB6QKv#@U#g%P%>dPcdFnfTMuYyOFExhXoymEYRVptmHjbgR</ZpjQU2&UMjzwJwnV70-CiM}M-OvR%a)Vk3R}2>wDegwAFAmjapXOejj</xMphtS-F8MVM^u2n5tN2DthGw{KZ`y7)>gEpaCbApodfJVPXVuct<bbgnEsMI_M%9L=E v)f6GjS_kSK6W5HWdel)VbBvZRG)#&b=+6k(O9=&C>rulVew89#uyWF}4`T\"xULOZ%1\"5Cu)&x7qD0</bbgnEsMI_M%9L=E v)f6GjS_kSK6W5HWdel)VbBvZRG)#&b=+6k(O9=&C><RicmJtmTo9uWkW^szFhT_OJ}_Jr4Galzm^v24RxPJkiUQN`vXV><ofMiPADbv&XoS=rU{r5JRfE+R}DgSHV@%zDk1golMD_IMuJ1U`~~TyD><lCERjrGdOfuVC0\"8W##`L8x+qC=HW><UnWhEk9TPbbij$y_M0J {0FKn*Gk`IHq88Z3I*18iOvbdA_><pXHzGgQgKWj}@x^Mr}7X_Sf6_P*b_{`A$$AouU&e81l}uy3ijn&K1BmXHY>nhPeJWSTIkb Gs_~ztKS*b#36E+ThD</pXHzGgQgKWj}@x^Mr}7X_Sf6_P*b_{`A$$AouU&e81l}uy3ijn&K1BmXHY></UnWhEk9TPbbij$y_M0J {0FKn*Gk`IHq88Z3I*18iOvbdA_>zBesDtLSpVYfUzgds</lCERjrGdOfuVC0\"8W##`L8x+qC=HW>OjuAcMjbwMLuylplc</RicmJtmTo9uWkW^szFhT_OJ}_Jr4Galzm^v24RxPJkiUQN`vXV>ZuWHgfxmI&l}D@r%q=mSj  Y&paOAZFf=~}I_Eg<CJQuDskoQL=5PG-ONFzA\"yYhcEB2#ize-4H%+^b=Db~p(1U%=UKeCt5ZWS>RCKchSzpkdh0oF aNt (%Vu ^ap3K0GP634h_k%1FsO$Wu0n1aeLEPSBHpGpHdWrIrIApQrorxMIBVbyhh</CJQuDskoQL=5PG-ONFzA\"yYhcEB2#ize-4H%+^b=Db~p(1U%=UKeCt5ZWS><XqPMRbmTQnlc#1 =N^z7~QuF2v(4}fd6^2BVJ8Wog4EHA~l%RNg_7Zb><RIEb0nii0bO5WV$%n#}TgB*fLl>RIAFwuGoRsU_9PleTRq{FS}fa3)1kRgn(C}ohOYXDqk</RIEb0nii0bO5WV$%n#}TgB*fLl>fQtRVddxdcuYJAikIUG</XqPMRbmTQnlc#1 =N^z7~QuF2v(4}fd6^2BVJ8Wog4EHA~l%RNg_7Zb>WmwMTg1FWlk0G6(mmh-SN\"k&hoKwzfF0PLel$2)aLS5tW22jz=zX9M7S88Hm_1aF7<rQNSVnYKC-ALi0RYY#sqP%J9CRykB_p14ifh@@#j0Svdm57N}{6_#QRpFytrvzk&E{$1>tULUXNsa`iS~T(_BQ`EqJNSgxcO)$`~d=`F2Dqbst4-_N</rQNSVnYKC-ALi0RYY#sqP%J9CRykB_p14ifh@@#j0Svdm57N}{6_#QRpFytrvzk&E{$1><MB=7g =8{_aUlwE_>MoUMPsYfBJNd$G~\"=dbO0U%aiOUaBBG&%(oFSfOZYvXSPFfwh@R QqyvTQ)3rJu%k6OBuBXkSShV=nm\"qJ46_VKi1- oHK^EdKTk~gs%t\"8<XnRH-gxlai2Jh`jmje=IUBZh`SUC\"et(=81U0_wyx_mnRD4>LfOJpqbcGhsTzasaaYU</XnRH-gxlai2Jh`jmje=IUBZh`SUC\"et(=81U0_wyx_mnRD4>YYYbEkrZ^wz=4046`&}N (^*_8EO^Eraw*jTFD~+IX%YX_Oo`f3BlFjmhSrEtE8FTe5870&+z(i@czXtI2mRfw7p^yFO_~r{*D0su6w&vnXb0aC+52Kol<byi)o%`Y{NBe>zYcZaQOKUCW4G0mJ$xe*xGIuY-&@1S`wkck%203&P{N}<lHrYNvRe#sQdvy-TurFDw+lUOyM1UI2sv`=><Nrp\"OjKiUXOhM(fRCqS\"C1-SmCTx%urq^=NEF-i*q&x><XKKZIu2zuIXam%aUp`bmJ(JwI`A"
        };

        Pattern pattern = Pattern.compile("(?:<([^<\\/>]+)>)+([^<\\/>]+)(?:<\\/\\1>)+");

        // <([\w\d\s]+)>+(.*)<\/\1*>
        // (<([\w\d\s]+)>)+([\w\d\s][^<\/>]*)(<\/\2*>)+
        // (?:(<([\w\d\s]+)>)+)([\w\d\s]*[^<\/>]*)(?:(<\/\2+>)+)
        // (?:<([\w\d\s]+)>)+([\w\s\d]*[^<\/>]*)(?:<\/\1>)+
        // (?:<([\w\d\s]+[^<\/>]*)>)+([\w\s\d]*[^<\/>]*)(?:<\/\1>)+
        // (?:<([^<\/>]*)>)+([^<\/>]*)(?:<\/\1>)+

        for (String line : inputLines) {
            boolean matchFound = false;
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()){
                System.out.println(matcher.group(2));
                matchFound = true;
            }

            if(!matchFound){
                System.out.println("None");
            }
        }


    }
}
