package com.example.myapplication05.ResumePackage.Activity.Tabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.File;

public class SharePreferance {
    public static final String DOB = "dob";
    public static final String MARITAL_STATUS = "marriage";
    public static final String LANGUAGE = "lang";
    public static final String NATIONALITY = "nationality";
    public static final String FNAME = "fname";
    public static final String M_COURSE="mcourse";
    public static final String M_STREAM="mstream";
    public static final String M_INSTITUTE="minstitute";
    public static final String M_CGPA="mcgpa";
    public static final String M_YEAR="myear";
    public static String PREF_NAME = "myPref";
    public static final String B_COURSE="bcourse";
    public static final String B_STREAM="bstream";
    public static final String B_INSTITUTE="binstitute";
    public static final String B_CGPA="bcgpa";
    public static final String B_YEAR="byear";
    public static final String T_COURSE="tcourse";
    public static final String T_STREAM="tstream";
    public static final String T_INSTITUTE="tinstitute";
    public static final String T_CGPA="tcgpa";
    public static final String T_YEAR="tyear";
    public static final String H_COURSE="hcourse";
    public static final String H_INSTITUTE="hinstitute";
    public static final String H_CGPA="hcgpa";
    public static final String H_YEAR="hyear";
    public static final String NAME = "name";
    public static final String ADD_1 = "add1";
    public static final String ADD_2 = "add2";
    public static final String ADD_3 = "add3";
    public static final String EMAIL = "email";
    public static final String PHONE = "phone";
    public static final String LIST="list";
    public static final String TITLE="title";
    public static final String DESCRIPTION="description";
    public static final String DURATION2="duration";
    public static final String SKILLS="skills";
    public static final String INTERESTS="interests";
    public static final String INDUSTRIAL="industry";
    public static final String AWARDS="achievements";
    public static final String ACTIVITY="activities";
    public static final String STRENGTHS="strengths";
    public static final String HOBBIES="hobbies";
    public static final String OBJECTIVE="objective";
    public static final String DECLARATION="declaration";
    public static final String BASIC_HEADING="basicDetails";
    public static final String PERSONAL_HEADING="personalInfo";
    public static final String EDUCATION_HEADING="education";
    public static final String EXPERIENCE_HEADING="experience";
    public static final String PROJECTS_HEADING="projects";
    public static final String TECHNICAL_HEADING="technicalSkills";
    public static final String INTERESTS_HEADING="interests";
    public static final String INDUSTRY_HEADING="industrial";
    public static final String AWARDS_HEADING="awardsandac";
    public static final String ACTIVITY_HEADING="activit";
    public static final String HOBBY_HEADING="hobbie";
    public static final String DATE_HEADING="dateDec";
    public static final String REFERENCE_HEADING="references";
    public static final String PHOTOS_HEADING="phot";
    public static final String REF_NAME="rname";
    public static final String REF_DESIG="rdesig";
    public static final String REF_ORG="rorg";
    public static final String REF_EMAIL="remail";
    public static final String REF_PHONE="rphone";
    public static final String PHOTO="photo";
    public static final String PLACE="place";
    public static final String SIGNATURE="signature";

    public static final String DATE="date";
    private SharedPreferences sharePreferance;
    private SharedPreferences.Editor editor;

    public SharePreferance(Context context){

        sharePreferance = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor = sharePreferance.edit();
    }

    public void saveBachelor(String course, String stream, String institute, String cgpa, String passingYear){
        editor.putString(B_COURSE,course);
        editor.putString(B_STREAM,stream);
        editor.putString(B_INSTITUTE,institute);
        editor.putString(B_CGPA,cgpa);
        editor.putString(B_YEAR,passingYear);
        editor.commit();
    }
    public void saveTenth(String course,  String institute, String cgpa, String passingYear){
        editor.putString(H_COURSE,course);
        editor.putString(H_INSTITUTE,institute);
        editor.putString(H_CGPA,cgpa);
        editor.putString(H_YEAR,passingYear);
        editor.commit();
    }
    public void saveTwelth(String course, String stream, String institute, String cgpa, String passingYear){
        editor.putString(T_COURSE,course);
        editor.putString(T_STREAM,stream);
        editor.putString(T_INSTITUTE,institute);
        editor.putString(T_CGPA,cgpa);
        editor.putString(T_YEAR,passingYear);
        editor.commit();
    }
    public void saveMaster(String course, String stream, String institute, String cgpa, String passingYear){
        editor.putString(M_COURSE,course);
        editor.putString(M_STREAM,stream);
        editor.putString(M_INSTITUTE,institute);
        editor.putString(M_CGPA,cgpa);
        editor.putString(M_YEAR,passingYear);
        editor.commit();
    }
    public void savePersonalInfo( String dob, String marriage, String language, String nationality,String fname){
        editor.putString(DOB,dob);
        editor.putString(MARITAL_STATUS,marriage);
        editor.putString(LANGUAGE,language);
        editor.putString(NATIONALITY,nationality);
        editor.putString(FNAME,fname);
        editor.commit();
    }
    public void saveDateDeclaration( String objective, String declaration, String date,String place){
        editor.putString(OBJECTIVE,objective);
        editor.putString(DECLARATION,declaration);
        editor.putString(PLACE,place);
        editor.putString(DATE,date);
        editor.commit();
    }
    public void savePhotoandSign( String photo, String sign){
        editor.putString(PHOTO,photo);
        editor.putString(SIGNATURE,sign);
        editor.commit();
    }
    public void saveBasicDetails( String name, String add1, String add2, String add3,String email,String phone){
        editor.putString(NAME,name);
        editor.putString(ADD_1,add1);
        editor.putString(ADD_2,add2);
        editor.putString(ADD_3,add3);
        editor.putString(EMAIL,email);
        editor.putString(PHONE,phone);
        editor.commit();
    }
    public void saveReferenceDetails( String name, String designation, String organization, String email,String phone){
        editor.putString(REF_NAME,name);
        editor.putString(REF_DESIG,designation);
        editor.putString(REF_ORG,organization);
        editor.putString(REF_EMAIL,email);
        editor.putString(REF_PHONE,phone);
        editor.commit();
    }
    public void saveProjectDetails(String title, String dscription, String duration){
        editor.putString(TITLE,title);
        editor.putString(DESCRIPTION,dscription);
        editor.putString(DURATION2,duration);
        editor.commit();
    }
    public void saveTechnicalSkills(String skills){
        editor.putString(SKILLS,skills);
        editor.commit();
    }
    public void saveStrengthsandHobbies(String strengths,String hobbies){
        editor.putString(STRENGTHS,strengths);
        editor.putString(HOBBIES,hobbies);
        editor.commit();
    }
    public void saveInterests(String interests){
        editor.putString(INTERESTS,interests);
        editor.commit();
    }
    public void saveIndustrial(String industry){
        editor.putString(INDUSTRIAL,industry);
        editor.commit();
    }
    public void saveActivity(String activity){
        editor.putString(ACTIVITY,activity);
        editor.commit();
    }
    public void saveAwards(String awards){
        editor.putString(AWARDS,awards);
        editor.commit();
    }
    public void saveExperienceList(String liste){
        editor.putString(LIST,liste);
        editor.commit();
    }
    public void saveHeadingBasic(String heading){
        editor.putString(BASIC_HEADING,heading);
        editor.commit();
    }
    public void saveHeadingPersonal(String heading){
        editor.putString(PERSONAL_HEADING,heading);
        editor.commit();
    }
    public void saveHeadingEducation(String heading){ editor.putString(EDUCATION_HEADING,heading);
    editor.commit();}
    public void saveHeadingExperience(String heading){
        editor.putString(EXPERIENCE_HEADING,heading);
        editor.commit();
    }
    public void saveHeadingProjects(String heading){
        editor.putString(PROJECTS_HEADING,heading);
        editor.commit();
    }
    public void saveHeadingTechnical(String heading){
        editor.putString(TECHNICAL_HEADING,heading);
        editor.commit();
    }
    public void saveHeadingInterests(String heading){
        editor.putString(INTERESTS_HEADING,heading);
        editor.commit();
    }
    public void saveHeadingIndustry(String heading){
        editor.putString(INDUSTRY_HEADING,heading);
        editor.commit();
    }
    public void saveHeadingAwards(String heading) {
        editor.putString(AWARDS_HEADING,heading);
        editor.commit();
    }
    public void saveHeadingActivities(String heading){
        editor.putString(ACTIVITY_HEADING,heading);
        editor.commit();
    }    public void saveHeadingHobby(String heading){
        editor.putString(HOBBY_HEADING,heading);
        editor.commit();
    }
    public void saveHeadingDate(String heading){
        editor.putString(DATE_HEADING,heading);
        editor.commit();
    }
    public void saveHeadingReference(String heading){
        editor.putString(REFERENCE_HEADING,heading);
        editor.commit();
    }    public void saveHeadingPhoto(String heading){
        editor.putString(PHOTOS_HEADING,heading);
    editor.commit();}


    public String getBachelors(String key){
        return sharePreferance.getString(key,"H");
    }
    public String getMaster(String key){
        return sharePreferance.getString(key,"H");
    }
    public String getTwelth(String key){
        return sharePreferance.getString(key,"H");
    }
    public String getTenth(String key){
        return sharePreferance.getString(key,"H");
    }
    public String getAwards(String key){
        return sharePreferance.getString(key,"H");
    }
    public String getPersonalInfo(String key){
        return sharePreferance.getString(key,"H");
    }
    public String getBasicDetails(String key){
        return sharePreferance.getString(key,"H");
    }
    public String getProjectDetails(String key){
        return sharePreferance.getString(key,"H");
    }
    public String getTechnicalSkills(String key){
        return sharePreferance.getString(key,"H");
    }
    public String getInterests(String key){
        return sharePreferance.getString(key,"H");
    }
    public String getIndustrial(String key){
        return sharePreferance.getString(key,"H");
    }
    public String getActivity(String key){
        return sharePreferance.getString(key,"H");
    }
    public String getStrengthsandHobbies(String key){
        return sharePreferance.getString(key,"H");
    }
    public String getDateDeclaration(String key){ return sharePreferance.getString(key,"H");}
    public String getHeadingBasic(String key){ return sharePreferance.getString(key,"Basic Details");}
    public String getHeadingPersonal(String key){ return sharePreferance.getString(key,"Personal Details");}
    public String getHeadingInterests(String key){ return sharePreferance.getString(key,"Personal Details");}
    public String getHeadingEducation(String key){ return sharePreferance.getString(key,"Education");}
    public String getHeadingIndustry(String key){ return sharePreferance.getString(key,"Experience");}
    public String getHeadingExperience(String key){ return sharePreferance.getString(key,"Experience");}
    public String getHeadingProject(String key){ return sharePreferance.getString(key,"Project");}
    public String getHeadingTechnical(String key){ return sharePreferance.getString(key,"Technical Skills");}
    public String getHeadingAwards(String key){ return sharePreferance.getString(key,"Achievement and Awards");}
    public String getHeadingActivity(String key){ return sharePreferance.getString(key,"Activities");}
    public String getHeadingHobby(String key){ return sharePreferance.getString(key,"Hobbies and Strength");}
    public String getHeadingDate(String key){ return sharePreferance.getString(key,"Date");}
    public String getHeadingReference(String key){ return sharePreferance.getString(key,"Reference Details");}
    public String getHeadingPhoto(String key){ return sharePreferance.getString(key,"H");}
    public String getReferenceDetails(String key){ return sharePreferance.getString(key,"H");}
    public String getPhotoandSign(String key){ return sharePreferance.getString(key,"H");}
    public String getExperience(String key){ return sharePreferance.getString(key,"");}

}
