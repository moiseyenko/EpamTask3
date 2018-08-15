import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

public class Exchange {

	public static void main(String[] args) {
		Broker br1 = new Broker("Broker1", 10, 10, 10);
		Broker br2 = new Broker("Broker2", 10, 10, 10);
		for (int i = 10; i > 0; i--) {
			makeDeal(br1, br2);
			System.out.println(i + "!!!!\n" + br1 + "\n" + br2 + "\n\n");
		}	
		
		 
		//double in = 1.9; double index =  1/in; //System.out.println((int)
		 // System.out.println(index);
		  //Math.ceil((amount * index))); for (int i = 100; i > 0; i--)
		  //System.out.println(index);
		  //System.out.println(BigDecimal.valueOf(index).setScale(3, RoundingMode.HALF_UP).doubleValue()); 
		 

	}

	public static void makeDeal(Broker br1, Broker br2) {
		// 0)"USD"
		// 1)"EUR"
		int currencyTo = new Random().nextInt(2);
		double amountFrom = (new Random().nextDouble() * 20) - 10.0;
		double index;
		double amountTo;

		// BYN to USD
		if (currencyTo == 0) {
			Broker tempBr1 = new Broker(br1);
			Broker tempBr2 = new Broker(br2);
			if (amountFrom < 0) {
				index = 1 / br1.getIndexUsd();
				br1.setIndexUsd(br1.getIndexUsd()-0.1);
				br2.setIndexUsd(br2.getIndexUsd()+0.1);
				//br1.setIndexUsd(BigDecimal.valueOf(br1.getIndexUsd()-0.1).setScale(3, RoundingMode.HALF_UP).doubleValue());
				//br2.setIndexUsd(BigDecimal.valueOf(br2.getIndexUsd()+0.1).setScale(3, RoundingMode.HALF_UP).doubleValue());
			} else {
				index = (double) 1 / br2.getIndexUsd();
				br1.setIndexUsd(br1.getIndexUsd()+0.1);
				br2.setIndexUsd(br2.getIndexUsd()-0.1);
				//br1.setIndexUsd(BigDecimal.valueOf(br1.getIndexUsd()+0.1).setScale(3, RoundingMode.HALF_UP).doubleValue());
				//br2.setIndexUsd(BigDecimal.valueOf(br2.getIndexUsd()-0.1).setScale(3, RoundingMode.HALF_UP).doubleValue());
			}
			amountTo = amountFrom * index;
			//set new amounts
			br1.setByn(BigDecimal.valueOf(br1.getByn() - amountFrom).setScale(4, RoundingMode.HALF_UP).doubleValue());
			br2.setByn(BigDecimal.valueOf(br2.getByn() + amountFrom).setScale(4, RoundingMode.HALF_UP).doubleValue());
			br1.setUsd(BigDecimal.valueOf(br1.getUsd() + amountTo).setScale(4, RoundingMode.HALF_UP).doubleValue());
			br2.setUsd(BigDecimal.valueOf(br2.getUsd() - amountTo).setScale(4, RoundingMode.HALF_UP).doubleValue());
			//deal cancel
			if (br1.getByn() < 0 || br2.getUsd() < 0||br1.getUsd()<0||br2.getByn()<0) {
				br1.setByn(tempBr1.getByn());
				br1.setUsd(tempBr1.getUsd());
				br1.setIndexUsd(tempBr1.getIndexUsd());
				br2.setByn(tempBr2.getByn());
				br2.setUsd(tempBr2.getUsd());
				br2.setIndexUsd(tempBr2.getIndexUsd());
				System.out.println("В сделке отказано!");
			}
		} else {
			// BYN to EUR
			Broker tempBr1 = new Broker(br1);
			Broker tempBr2 = new Broker(br2);
			if (amountFrom < 0) {
				index = 1 / br1.getIndexEur();
				br1.setIndexEur(br1.getIndexEur()-0.1);
				br2.setIndexEur(br2.getIndexEur()+0.1);
				//br1.setIndexEur(BigDecimal.valueOf(br1.getIndexEur()-0.1).setScale(3, RoundingMode.HALF_UP).doubleValue());
				//br2.setIndexEur(BigDecimal.valueOf(br2.getIndexEur()+0.1).setScale(3, RoundingMode.HALF_UP).doubleValue());
			} else {
				index = (double) 1 / br2.getIndexEur();
				br1.setIndexEur(br1.getIndexEur()+0.1);
				br2.setIndexEur(br2.getIndexEur()-0.1);
				//br1.setIndexEur(BigDecimal.valueOf(br1.getIndexEur()+0.1).setScale(3, RoundingMode.HALF_UP).doubleValue());
				//br2.setIndexEur(BigDecimal.valueOf(br2.getIndexEur()-0.1).setScale(3, RoundingMode.HALF_UP).doubleValue());
			}
			amountTo = amountFrom * index;
			//set new amounts
			br1.setByn(BigDecimal.valueOf(br1.getByn() - amountFrom).setScale(4, RoundingMode.HALF_UP).doubleValue());
			br2.setByn(BigDecimal.valueOf(br2.getByn() + amountFrom).setScale(4, RoundingMode.HALF_UP).doubleValue());
			br1.setEur(BigDecimal.valueOf(br1.getEur() + amountTo).setScale(4, RoundingMode.HALF_UP).doubleValue());
			br2.setEur(BigDecimal.valueOf(br2.getEur() - amountTo).setScale(4, RoundingMode.HALF_UP).doubleValue());
			//deal cancel
			if (br1.getByn() < 0 || br2.getEur() < 0||br1.getEur()<0||br2.getByn()<0) {
				br1.setByn(tempBr1.getByn());
				br1.setEur(tempBr1.getEur());
				br1.setIndexEur(tempBr1.getIndexEur());
				br2.setByn(tempBr2.getByn());
				br2.setEur(tempBr2.getEur());
				br2.setIndexEur(tempBr2.getIndexEur());
				System.out.println("В сделке отказано!");
			}
		} 
	}

}
