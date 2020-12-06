package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        String link_image1 = "https://bit.ly/3lEFm6o";
        String link_image2 = "https://bit.ly/2IjzC4u";
        String faceId1, faceId2;
        //faceId1 = Requests.postDetectRequest(link_image1);
        //faceId2 = Requests.postDetectRequest(link_image2);
        //System.out.println(Requests.postVerifyRequest(faceId1, faceId2));

        Requests.postImageReferenceRequest("C:\\Users\\ediol\\Desktop\\asd.jpg");
    }



}
