package com.jpackag.camera;

import java.text.DecimalFormat;

public class ObjectTracker {

	public static void main(String[] args) {
//	Raw input data provided for each input file
// input0.txt		
		/*
		 * double
		 * data[]={70.9,71.9,72.3,73.2,73.9,77.2,77.2,79.4,79.8,82.3,83.3,85.8,87.3,152.
		 * 3,
		 * 169.6,170.7,176.3,176.9,177.2,179.4,180.1,181.2,181.9,182.0,182.6,185.5,188.
		 * 5,
		 * 338.1,338.7,339.8,340.0,340.2,344.1,344.2,344.8,347.3,348.5,350.4,351.3,351.8
		 * };
		 */
// input1.txt	
		/*
		 * double
		 * data[]={36.9,39.4,40.7,45.9,48.4,49.3,50.4,51.1,51.6,55.2,57.8,142.0,181.6,
		 * 205.9,209.6,214.7,
		 * 216.0,220.5,221.1,221.9,222.0,222.5,224.2,224.5,226.1,280.9,287.4,288.3,290.0
		 * ,290.1, 93.3,294.5,295.5,296.0,297.0,298.7};
		 */
// input2.txt
		/*
		 * double
		 * data[]={0.2,1.2,3.1,3.2,4.1,5.9,51.8,56.1,56.5,57.8,59.3,59.5,60.9,61.1,61.6,
		 * 61.7,62.9,63.0,
		 * 65.1,66.9,68.0,142.4,169.2,182.0,183.4,185.4,186.5,189.2,189.4,189.8,190.2,
		 * 192.9,193.4, 194.3,198.2,266.1,
		 * 278.6,279.9,281.1,285.2,285.4,285.4,285.5,285.6,287.1,290.5,350.3,353.5,359.5
		 * ,359.8} ;
		 */
// input3.txt
		/*
		 * double
		 * data[]={2.1,4.2,4.7,5.3,5.7,6.8,7.9,9.4,11.1,57.5,88.7,92.6,94.7,95.0,95.0,95
		 * .0,96.0,98.4,99.3,99.4,101.9,
		 * 104.7,105.3,157.7,160.1,160.8,163.4,165.1,165.9,167.5,168.9,169.5,170.5,170.8
		 * ,230.7,348.5,354.2,355.5,359.1} ;
		 */
// input4.txt
		double data[] = { 23.7, 112.5, 113.3, 114.1, 115.4, 116.7, 118.3, 118.6, 119.7, 120.7, 122.4, 123.0, 175.3,
				179.9, 183.8, 185.4, 188.9, 188.9, 190.1, 191.0, 192.4, 192.8, 192.9, 193.2, 194.2, 196.8, 243.2, 243.7,
				248.9, 250.2, 250.4, 251.5, 252.8, 253.6, 253.8, 254.1, 254.5, 257.1, 260.5, 312.9, 317.0, 318.5, 318.7,
				321.1, 322.7, 323.4, 325.4, 326.1, 326.2, 326.3, 326.3, 327.4 };

		int noofclusters = 3;
		double centroid[][] = new double[][] { { 0.0, 0.0, 0.0 },
// for input0.txt			
//			{36.9 ,181.6,288.3}
// for input1.txt			
//			{51.8 ,142.4,278.6}

// for input4.txt since first cluster has only one reading in (0<= theta <=90, could be an outlier, change initial centroid to
				{ 112.5, 183.8, 278.6 }

		};
		getCentroid(data, noofclusters, centroid);

	}

	public static double[][] getCentroid(double data[], int noofclusters, double centroid[][]) {

		double distance[][] = new double[noofclusters][data.length];
		int cluster[] = new int[data.length];
		int clusternodecount[] = new int[noofclusters];

		DecimalFormat df2 = new DecimalFormat("#.##");

		centroid[0] = centroid[1];
		centroid[1] = new double[] { 0, 0, 0 };
		System.out.println("========== Starting to get new centroid =========");

		for (int i = 0; i < noofclusters; i++) {
			for (int j = 0; j < data.length; j++) {
				// System.out.println(distance[i][j]+"("+i+","+j+")="+data[j]+"("+j+")-"+centroid[0][i]+"="+(data[j]-centroid[0][i]));
				distance[i][j] = Math.abs(data[j] - centroid[0][i]);
				System.out.print(df2.format(distance[i][j]) + " ,");
				// System.out.println("Centroid: "+centroid[0][i]);
			}
			System.out.println();
		}

		for (int j = 0; j < data.length; j++) {
			int smallerDistance = 0;
			if (distance[0][j] < distance[1][j] && distance[0][j] < distance[2][j])
				smallerDistance = 0;
			if (distance[1][j] < distance[0][j] && distance[1][j] < distance[2][j])
				smallerDistance = 1;
			if (distance[2][j] < distance[0][j] && distance[2][j] < distance[1][j])
				smallerDistance = 2;

			centroid[1][smallerDistance] = centroid[1][smallerDistance] + data[j];
			clusternodecount[smallerDistance] = clusternodecount[smallerDistance] + 1;
			cluster[j] = smallerDistance;

//			System.out.println("Centerid at 1:  "+centroid[1][smallerDistance]);
//			System.out.print(cluster[j]+", ");

		}
		for (int j = 0; j < data.length; j++)
			System.out.println("c at out: " + cluster[j]);

		System.out.println("======================================== ");

		System.out.println("New clusters are ");

		for (int i = 0; i < noofclusters; i++) {
			System.out.print("C" + (i + 1) + ": ");
			for (int l = 0; l < data.length; l++) {
				if (cluster[l] == i)
					System.out.print(data[l] + " ,");

			}
			System.out.println();
		}
		System.out.println("======================================== ");

		System.out.println("New centroid is ");

		for (int j = 0; j < noofclusters; j++) {
			centroid[1][j] = centroid[1][j] / clusternodecount[j];
//			System.out.print(centroid[1][j]+",");

			System.out.print(" " + df2.format(centroid[1][j]) + " ");
		}
		System.out.println();

		boolean isAchived = true;
		for (int j = 0; j < noofclusters; j++) {
			if (isAchived && centroid[0][j] == centroid[1][j]) {
				isAchived = true;
				continue;
			}
			isAchived = false;
		}

		if (!isAchived) {

			getCentroid(data, noofclusters, centroid);
		}

		if (isAchived) {
			System.out.println("======================================== ");
			System.out.println(" Final Cluster is ");
			for (int i = 0; i < noofclusters; i++) {
				System.out.print("C" + (i + 1) + ":");
				for (int j = 0; j < data.length; j++) {
					if (cluster[j] == i)

						System.out.print(data[j] + " ,");

				}
				System.out.println();
			}
		}

		return centroid;

	}
}
/*
 * Testing and data analysis 
 * Input data, respective output answer provided, computed centroid , and
 * cleansing/analysis of output data
 * 
 * Output for input0.txt (output0 : 76.2,179.4,342.6 ) New Centroid is 78.04
 * 177.44 344.55 ======================================== Final Cluster is
 * C1:70.9 ,71.9 ,72.3 ,73.2 ,73.9 ,77.2 ,77.2 ,79.4 ,79.8 ,82.3 ,83.3 ,85.8
 * ,87.3 , C2:152.3 ,169.6 ,170.7 ,176.3 ,176.9 ,177.2 ,179.4 ,180.1 ,181.2
 * ,181.9 ,182.0 ,182.6 ,185.5 ,188.5 , C3:338.1 ,338.7 ,339.8 ,340.0 ,340.2
 * ,344.1 ,344.2 ,344.8 ,347.3 ,348.5 ,350.4 ,351.3 ,351.8 ,
 * 
 * 
 * 
 * Output for input1.txt (output1 : 48.3,220.1,290.3 ) New Centroid is 51.67
 * 210.9 291.84 ======================================== Final Cluster is
 * C1:36.9 ,39.4 ,40.7 ,45.9 ,48.4 ,49.3 ,50.4 ,51.1 ,51.6 ,55.2 ,57.8 ,93.3 ,
 * C2:142.0 ,181.6 ,205.9 ,209.6 ,214.7 ,216.0 ,220.5 ,221.1 ,221.9 ,222.0
 * ,222.5 ,224.2 ,224.5 ,226.1 , C3:280.9 ,287.4 ,288.3 ,290.0 ,290.1 ,294.5
 * ,295.5 ,296.0 ,297.0 ,298.7 ,
 * 
 * Output for input2.txt (output2 : 48.3,220.1,290.3) New Centroid is 44.28
 * 184.74 302.23 ======================================== Final Cluster is
 * C1:0.2 ,1.2 ,3.1 ,3.2 ,4.1 ,5.9 ,51.8 ,56.1 ,56.5 ,57.8 ,59.3 ,59.5 ,60.9
 * ,61.1 ,61.6 ,61.7 ,62.9 ,63.0 ,65.1 ,66.9 ,68.0 , C2:142.4 ,169.2 ,182.0
 * ,183.4 ,185.4 ,186.5 ,189.2 ,189.4 ,189.8 ,190.2 ,192.9 ,193.4 ,194.3 ,198.2
 * , C3:266.1 ,278.6 ,279.9 ,281.1 ,285.2 ,285.4 ,285.4 ,285.5 ,285.6 ,287.1
 * ,290.5 ,350.3 ,353.5 ,359.5 ,359.8 ,
 * 
 * 
 * for input3.txt
 * 
 * New Centroid is 11.47 132.68 354.33 ========================================
 * Final Cluster is C1:2.1 ,4.2 ,4.7 ,5.3 ,5.7 ,6.8 ,7.9 ,9.4 ,11.1 ,57.5 ,
 * C2:88.7 ,92.6 ,94.7 ,95.0 ,95.0 ,95.0 ,96.0 ,98.4 ,99.3 ,99.4 ,101.9 ,104.7
 * ,105.3 ,157.7 ,160.1 ,160.8 ,163.4 ,165.1 ,165.9 ,167.5 ,168.9 ,169.5 ,170.5
 * ,170.8 ,230.7 , C3:348.5 ,354.2 ,355.5 ,359.1 ,
 * 
 * for input4.txt sensitivity data analysis, based on input data observation, I
 * run two sets to fix outlier single input 23.7 New Centroid is 23.7 157.61
 * 287.17 ======================================== Final Cluster is C1:23.7 ,
 * C2:112.5 ,113.3 ,114.1 ,115.4 ,116.7 ,118.3 ,118.6 ,119.7 ,120.7 ,122.4
 * ,123.0 ,175.3 ,179.9 ,183.8 ,185.4 ,188.9 ,188.9 ,190.1 ,191.0 ,192.4 ,192.8
 * ,192.9 ,193.2 ,194.2 ,196.8 , C3:243.2 ,243.7 ,248.9 ,250.2 ,250.4 ,251.5
 * ,252.8 ,253.6 ,253.8 ,254.1 ,254.5 ,257.1 ,260.5 ,312.9 ,317.0 ,318.5 ,318.7
 * ,321.1 ,322.7 ,323.4 ,325.4 ,326.1 ,326.2 ,326.3 ,326.3 ,327.4 ,
 * 
 * Second data input set, based on my observation, to get more realistic output
 * data for centriod output prediction ouput : New Centroid is 109.87 188.97
 * 287.17 ======================================== Final Cluster is C1:23.7
 * ,112.5 ,113.3 ,114.1 ,115.4 ,116.7 ,118.3 ,118.6 ,119.7 ,120.7 ,122.4 ,123.0
 * , C2:175.3 ,179.9 ,183.8 ,185.4 ,188.9 ,188.9 ,190.1 ,191.0 ,192.4 ,192.8
 * ,192.9 ,193.2 ,194.2 ,196.8 , C3:243.2 ,243.7 ,248.9 ,250.2 ,250.4 ,251.5
 * ,252.8 ,253.6 ,253.8 ,254.1 ,254.5 ,257.1 ,260.5 ,312.9 ,317.0 ,318.5 ,318.7
 * ,321.1 ,322.7 ,323.4 ,325.4 ,326.1 ,326.2 ,326.3 ,326.3 ,327.4 ,
 * 
 * 
 */
