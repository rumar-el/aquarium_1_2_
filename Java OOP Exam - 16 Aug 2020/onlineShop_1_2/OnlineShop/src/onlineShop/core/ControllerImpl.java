package onlineShop.core;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.common.constants.OutputMessages;
import onlineShop.core.interfaces.Controller;
import onlineShop.models.products.components.*;
import onlineShop.models.products.computers.Computer;
import onlineShop.models.products.computers.DesktopComputer;
import onlineShop.models.products.computers.Laptop;
import onlineShop.models.products.peripherals.*;

import java.util.ArrayList;
import java.util.List;

public class ControllerImpl implements Controller {
    List<Computer> computers;
    List<Component> components;
    List<Peripheral> peripherals;

    public ControllerImpl(){
        computers = new ArrayList<>();
        components = new ArrayList<>();
        peripherals = new ArrayList<>();
    }

    @Override
    public String addComputer(String computerType, int id, String manufacturer, String model, double price) {
        Computer computer;
        if(computerType.equals("DesktopComputer")) {
            computer = new DesktopComputer(id, manufacturer, model, price);
        } else if(computerType.equals("Laptop")) {
            computer = new Laptop(id, manufacturer, model, price);
        } else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPUTER_TYPE);
        }

        for (Computer c : computers) {
            if(c.getId() == computer.getId()){
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPUTER_ID);
            }
        }
        computers.add(computer);
        return String.format(OutputMessages.ADDED_COMPUTER, id);
    }

    @Override
    public String addPeripheral(int computerId, int id, String peripheralType, String manufacturer, String model, double price, double overallPerformance, String connectionType) {
        validateComputerId(computerId);
        Peripheral peripheral;
        if(peripheralType.equals("Headset")){
            peripheral = new Headset(id, manufacturer, model, price, overallPerformance, connectionType);
        }else if(peripheralType.equals("Keyboard")) {
            peripheral = new Keyboard(id, manufacturer, model, price, overallPerformance, connectionType);
        }else if(peripheralType.equals("Monitor")) {
            peripheral = new Monitor(id, manufacturer, model, price, overallPerformance, connectionType);
        }else if(peripheralType.equals("Mouse")) {
            peripheral = new Mouse(id, manufacturer, model, price, overallPerformance, connectionType);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_PERIPHERAL_TYPE);
        }

        for (Peripheral p : peripherals) {
            if(p.getId() == id){
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_PERIPHERAL_ID);
            }
        }

        peripherals.add(peripheral);
        for (Computer computer : computers) {
            if(computer.getId() == computerId) {
                computer.addPeripheral(peripheral);
                break;
            }
        }
        return String.format(OutputMessages.ADDED_PERIPHERAL, peripheralType, id, computerId);
    }


    @Override
    public String removePeripheral(String peripheralType, int computerId) {
        validateComputerId(computerId);
        Peripheral peripheral = null;
        for (Computer computer : computers) {
            peripheral = computer.removePeripheral(peripheralType);
        }
        for (Peripheral p : peripherals) {
            if(p.equals(peripheral)){
                peripherals.remove(p);
                break;
            }
        }
        return String.format(OutputMessages.REMOVED_PERIPHERAL, peripheralType, peripheral.getId());
    }

    @Override
    public String addComponent(int computerId, int id, String componentType, String manufacturer, String model, double price, double overallPerformance, int generation) {
        validateComputerId(computerId);
        Component component;
        if(componentType.equals("CentralProcessingUnit")){
            component = new CentralProcessingUnit(id, manufacturer, model, price, overallPerformance, generation);
        }else if(componentType.equals("Motherboard")) {
            component = new Motherboard(id, manufacturer, model, price, overallPerformance, generation);
        }else if(componentType.equals("PowerSupply")) {
            component = new PowerSupply(id, manufacturer, model, price, overallPerformance, generation);
        }else if(componentType.equals("RandomAccessMemory")) {
            component = new RandomAccessMemory(id, manufacturer, model, price, overallPerformance, generation);
        }else if(componentType.equals("SolidStateDrive")) {
            component = new SolidStateDrive(id, manufacturer, model, price, overallPerformance, generation);
        }else if(componentType.equals("VideoCard")) {
            component = new VideoCard(id, manufacturer, model, price, overallPerformance, generation);
        }else {
            throw new IllegalArgumentException(ExceptionMessages.INVALID_COMPONENT_TYPE);
        }

        for (Component c : components) {
            if(c.getId() == id){
                throw new IllegalArgumentException(ExceptionMessages.EXISTING_COMPONENT_ID);
            }
        }

        components.add(component);
        for (Computer computer : computers) {
            if(computer.getId() == computerId) {
                computer.addComponent(component);
                break;
            }
        }

        return String.format(OutputMessages.ADDED_COMPONENT, componentType, id, computerId);
    }

    @Override
    public String removeComponent(String componentType, int computerId) {
        validateComputerId(computerId);
        Component component = null;
        for (Computer computer : computers) {
            component = computer.removeComponent(componentType);
        }
        for (Component c : components) {
            if(c.equals(component)){
                components.remove(c);
                break;
            }
        }
        return String.format(OutputMessages.REMOVED_COMPONENT, componentType, component.getId());
    }

    @Override
    public String buyComputer(int id) {
        validateComputerId(id);
        Computer computer = null;
        for (Computer c : computers) {
            if(c.getId() == id){
                computer = c;
                computers.remove(c);
                break;
            }
        }
        return computer.toString();
    }

    @Override
    public String BuyBestComputer(double budget) {
        boolean validPrice = false;
        for (Computer computer : computers) {
            if(computer.getPrice() <= budget){
                validPrice = true;
                break;
            }
        }

        if(!validPrice){
            throw new IllegalArgumentException(String.format(ExceptionMessages.CAN_NOT_BUY_COMPUTER, budget));
        }
        List<Computer> currentComputers = new ArrayList<>();

        for (Computer computer : computers) {
            if(computer.getPrice() <= budget){
                currentComputers.add(computer);
            }
        }

        Computer c = currentComputers.get(0);
        for (Computer currentComputer : currentComputers) {
            if(c.getOverallPerformance() < currentComputer.getOverallPerformance()) {
                c = currentComputer;
            }
        }
        return c.toString();
    }

    @Override
    public String getComputerData(int id) {
        validateComputerId(id);
        Computer computer = null;
        for (Computer c : computers) {
            if(c.getId() == id){
                computer = c;
                break;
            }
        }
        return computer.toString();
    }

    private void validateComputerId(int computerId) {
        boolean existingId = false;
        for (Computer computer : computers) {
            if(computer.getId() == computerId){
                existingId = true;
                break;
            }
        }
        if(!existingId) {
            throw new IllegalArgumentException(ExceptionMessages.NOT_EXISTING_COMPUTER_ID);
        }
    }
}
