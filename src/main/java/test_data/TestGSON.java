package test_data;

import com.google.gson.Gson;

public class TestGSON {
    public static void main(String[] args) {
//        testGSONSample();
//        testBuilderMethod();
        testDataArray();
    }

    private static void testDataArray(){
        String relativeDataFileLocation = "/src/main/java/test_data/CheapComputerDataList.json";
        ComputerData[] computerDatas = DataObjectBuilder.buildDataObjectFrom(relativeDataFileLocation, ComputerData[].class);
        for (ComputerData computerData : computerDatas) {
            System.out.println(computerData.getProcessor());
            System.out.println(computerData.getRam());
            System.out.println(computerData.getOs());
            System.out.println(computerData.getHdd());
            System.out.println(computerData.getSoftware());
        }
    }
    private static void testBuilderMethod(){
        String relativeDataFileLocation = "/src/main/java/test_data/CheapComputerDataList.json";
        ComputerData computerData = DataObjectBuilder.buildDataObjectFrom(relativeDataFileLocation, ComputerData.class);
//        System.out.println(computerData);

        System.out.println(computerData.getProcessor());
        System.out.println(computerData.getRam());
        System.out.println(computerData.getOs());
        System.out.println(computerData.getHdd());
        System.out.println(computerData.getSoftware());
    }

    private static void testGSONSample(){
        String JSONString = "{\n" +
                "  \"processor\": \"2.5GHZ\",\n" +
                "  \"ram\": \"4GB\",\n" +
                "  \"os\": \"MacOS\",\n" +
                "  \"hdd\": \"512GB\",\n" +
                "  \"software\": \"Photoshop\"\n" +
                "}";

        Gson gson = new Gson();
        ComputerData computerData = gson.fromJson(JSONString, ComputerData.class);
        System.out.println(computerData);

        System.out.println(gson.toJson(computerData));


    }
}
