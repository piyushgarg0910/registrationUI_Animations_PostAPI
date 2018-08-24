package com.example.android.pheramor.model;

/*
 * Created by Piyush Garg on 08/20/18.
 */

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationDetails implements Parcelable {


    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("height_feet")
    @Expose
    private Integer heightFeet;
    @SerializedName("height_inches")
    @Expose
    private Integer heightInches;
    @SerializedName("zip")
    @Expose
    private Integer zipCode;
    @SerializedName("description")
    @Expose
    private String desc;
    @SerializedName("image")
    @Expose
    private String profilePic;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("date_of_birth")
    @Expose
    private String dob;
    @SerializedName("race")
    @Expose
    private String mRace;
    @SerializedName("religion")
    @Expose
    private String mReligion;
    @SerializedName("interest_male")
    @Expose
    private Integer interestMale;
    @SerializedName("interest_female")
    @Expose
    private Integer interestFemale;
    @SerializedName("age_min")
    @Expose
    private Integer interestAgeMin;
    @SerializedName("age_max")
    @Expose
    private Integer interestAgeMax;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.email);
        parcel.writeString(this.password);
        parcel.writeString(this.firstName);
        parcel.writeString(this.lastName);
        parcel.writeInt(this.heightFeet);
        parcel.writeInt(this.heightInches);
        parcel.writeInt(this.zipCode);
        parcel.writeString(this.desc);
        parcel.writeString(this.profilePic);
        parcel.writeString(this.gender);
        parcel.writeString(this.dob);
        parcel.writeString(this.mRace);
        parcel.writeString(this.mReligion);
        parcel.writeInt(this.interestMale);
        parcel.writeInt(this.interestFemale);
        parcel.writeInt(this.interestAgeMin);
        parcel.writeInt(this.interestAgeMax);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        @Override
        public RegistrationDetails createFromParcel(Parcel parcel) {
            return new RegistrationDetails(parcel);
        }

        @Override
        public RegistrationDetails[] newArray(int i) {
            return new RegistrationDetails[i];
        }
    };

    public RegistrationDetails(Parcel in){
        this.email = in.readString();
        this.password = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.heightFeet = in.readInt();
        this.heightInches = in.readInt();
        this.zipCode = in.readInt();
        this.desc = in.readString();
        this.profilePic = in.readString();
        this.gender = in.readString();
        this.dob = in.readString();
        this.mRace = in.readString();
        this.mReligion = in.readString();
        this.interestMale = in.readInt();
        this.interestFemale = in.readInt();
        this.interestAgeMin = in.readInt();
        this.interestAgeMax = in.readInt();
    }

    public RegistrationDetails() {
        this.email = null;
        this.password = null;
        this.firstName = null;
        this.lastName = null;
        this.heightFeet = 5;
        this.heightInches = 0;
        this.zipCode = 0;
        this.desc = null;
        this.profilePic = null;
        this.gender = null;
        this.dob = null;
        this.mRace = null;
        this.mReligion = null;
        this.interestMale = 1;
        this.interestFemale = 1;
        this.interestAgeMin = 18;
        this.interestAgeMax = 60;
    }

    public RegistrationDetails(String email, String password, String firstName, String lastName,
                               Integer heightFeet, Integer heightInches, Integer zipCode,
                               String desc, String profilePic, String gender, String dob,
                               String mRace, String mReligion, Integer interestMale,
                               Integer interestFemale, Integer interestAgeMin,
                               Integer interestAgeMax) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.heightFeet = heightFeet;
        this.heightInches = heightInches;
        this.zipCode = zipCode;
        this.desc = desc;
        this.profilePic = profilePic;
        this.gender = gender;
        this.dob = dob;
        this.mRace = mRace;
        this.mReligion = mReligion;
        this.interestMale = interestMale;
        this.interestFemale = interestFemale;
        this.interestAgeMin = interestAgeMin;
        this.interestAgeMax = interestAgeMax;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getHeightFeet() {
        return heightFeet;
    }

    public void setHeightFeet(Integer heightFeet) {
        this.heightFeet = heightFeet;
    }

    public Integer getHeightInches() {
        return heightInches;
    }

    public void setHeightInches(Integer heightInches) {
        this.heightInches = heightInches;
    }

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getmRace() {
        return mRace;
    }

    public void setmRace(String mRace) {
        this.mRace = mRace;
    }

    public String getmReligion() {
        return mReligion;
    }

    public void setmReligion(String mReligion) {
        this.mReligion = mReligion;
    }

    public Integer getInterestMale() {
        return interestMale;
    }

    public void setInterestMale(Integer interestMale) {
        this.interestMale = interestMale;
    }

    public Integer getInterestFemale() {
        return interestFemale;
    }

    public void setInterestFemale(Integer interestFemale) {
        this.interestFemale = interestFemale;
    }

    public Integer getInterestAgeMin() {
        return interestAgeMin;
    }

    public void setInterestAgeMin(Integer interestAgeMin) {
        this.interestAgeMin = interestAgeMin;
    }

    public Integer getInterestAgeMax() {
        return interestAgeMax;
    }

    public void setInterestAgeMax(Integer interestAgeMax) {
        this.interestAgeMax = interestAgeMax;
    }

    public static Creator getCREATOR() {
        return CREATOR;
    }

    @Override
    public String toString(){
        return  "Post{" + "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", height_feet='" + heightFeet + '\'' +
                ", height_inches='" + heightInches + '\'' +
                ", zip='" + zipCode + '\'' +
                ", description='" + desc + '\'' +
                ", image='" + profilePic + '\'' +
                ", gender='" + gender + '\'' +
                ", date_of_birth='" + dob + '\'' +
                ", race='" + mRace + '\'' +
                ", religion='" + mReligion + '\'' +
                ", interest_male='" + interestMale + '\'' +
                ", interest_female='" + interestFemale + '\'' +
                ", age_min='" + interestAgeMin + '\'' +
                ", age_max='" + interestAgeMax + '\'' +
                '}';
    }
}
