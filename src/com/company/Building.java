package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Factory building
 * Contains:
 * 1. Factory name
 * 2. Factory production plan (Project)
 */
public class Building {
    //<factory name, factory production>
    public HashMap<String, Project> factories = new HashMap<String, Project>();
    private Lot totalLots = new Lot();


    public void letsGo() {
        totalLots.productionTime = 0;
        //System.out.println("Factory: | Total time: | Time: | Recipe: | Product: | Quantity: | Total quantity: ");
        System.out.println("Factory: | Time: | Recipe: | Product: | Quantity:");
        System.out.println("");
        startFactory();
        System.out.println("________________________________________________");
        System.out.println("");
        System.out.println("Total time: " + totalLots.productionTime);
        System.out.println("Total products:");
        for (Product elementTotal : totalLots.productsPool) {
            System.out.println(elementTotal.name + " - " + elementTotal.quantity);
        }
        ProductsStorage.print();
    }

    public void addFactory(String factoryName, Project projectName) {
        factories.put(factoryName, projectName);
    }

    private void addLot(Lot lot) {
        totalLots.productionTime += lot.productionTime;

            for (Product element : lot.productsPool) {
                Boolean isLotExist = false;
                for (Product elementTotal : totalLots.productsPool) {
                    if (element.name == elementTotal.name) {
                        elementTotal.quantity += element.quantity;
                        isLotExist = true;
                    }
                }
                if (!isLotExist) {
                    totalLots.productsPool.add(element);
                }
            }

    }

    private void startFactory() {
        ExecutorService executorService = Executors.newCachedThreadPool();


        for (Map.Entry<String, Project> element : factories.entrySet()) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    Lot tempLot = new Lot();
                    Integer isOutOfResources = 0;
                    while (isOutOfResources < 5) {
                        tempLot = element.getValue().production(element.getKey());
                        try {
                            if (tempLot.productionTime != 0) {
                                Thread.sleep(50*tempLot.productionTime);
                                addLot(tempLot);
                            } else {
                                Thread.sleep(100);
                                isOutOfResources++;
                            }
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
        }


        executorService.shutdown();
        try {
            executorService.awaitTermination(3000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
