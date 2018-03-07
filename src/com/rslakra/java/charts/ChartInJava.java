//Using windows; after extraction.

//In Console/Dos:

//set CLASSPATH=%CLASSPATH%;C:\Place_To_Where_Your_Jchart.jar_is_located

//In Linux:

//export CLASSPATH=$CLASSPATH:/directory_path/location_of_Jchart_jar.jar
//public class ChartInJava
//{
//JFreeChart barChart;
//DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//dataset.setValue(30, "x/y", "barchart_representation1");
//dataset.setValue(20, "x/y", "barchart_representation2");
//char = ChartFactory.createBarChart("Title: Just A Bar Chart Example",
//    			"Value", "Denotion", dataset,
//				PlotOrientation.VERTICAL, false, true, false);
// 
//JFreeChart pie_chart;
//DefaultPieDataset dataset = new DefaultPieDataset();
//    	dataset.setValue("potatoes", 60);
//    	dataset.setValue("tomatoes", 10);
//    	dataset.setValue("pears", 30);
//pie_chart = ChartFactory.createPieChart(
//    			"Quantity of Farm Products sold",
//				dataset,
//				true,
//				true,
//				false);
//                                
//BufferedImage image = chart.createBufferedImage(500,300);
//BufferedImage image2 = pie_chart.createBufferedImage(500,300);
// 
////  These JLabels may then be placed in a JPanel and voila!!
////  You have your Bar Graph and Pie Chart
//JLabel label1 = new JLabel();
//JLabel label2 = new JLabel();
//label1.setIcon(image);
//label2.setIcon(image2);
//
//}
