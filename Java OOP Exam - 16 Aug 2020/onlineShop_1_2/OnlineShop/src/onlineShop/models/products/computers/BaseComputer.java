package onlineShop.models.products.computers;

import onlineShop.common.constants.ExceptionMessages;
import onlineShop.models.products.BaseProduct;
import onlineShop.models.products.Product;
import onlineShop.models.products.components.Component;
import onlineShop.models.products.peripherals.Peripheral;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseComputer extends BaseProduct implements Computer {
    private List<Component> components;
    private List<Peripheral> peripherals;

    protected BaseComputer(int id, String manufacturer, String model, double price, double overallPerformance) {
        super(id, manufacturer, model, price, overallPerformance);
        this.components = new ArrayList<>();
        this.peripherals = new ArrayList<>();
    }

    @Override
    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public List<Peripheral> getPeripherals() {
        return this.peripherals;
    }

    @Override
    public void addComponent(Component component) {
        for (Component c : components) {
            if(c.getClass().getSimpleName().equals(component.getClass().getSimpleName())){
                throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_COMPONENT, component.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
            }
        }
        components.add(component);
    }

    @Override
    public Component removeComponent(String componentType) {
        Component component = null;
        for (Component c : components) {
            if(c.getClass().getSimpleName().equals(componentType)){
                component = c;
                break;
            }
        }
        if(components.isEmpty() || component == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_COMPONENT, componentType, this.getClass().getSimpleName(), this.getId()));
        }
        return this.components.remove(components.indexOf(component));
    }

    @Override
    public void addPeripheral(Peripheral peripheral) {
        for (Peripheral p : peripherals) {
            if(p.getClass().getSimpleName().equals(peripheral.getClass().getSimpleName())){
                throw new IllegalArgumentException(String.format(ExceptionMessages.EXISTING_PERIPHERAL, peripheral.getClass().getSimpleName(), this.getClass().getSimpleName(), this.getId()));
            }
        }
        peripherals.add(peripheral);
    }

    @Override
    public Peripheral removePeripheral(String peripheralType) {
        Peripheral peripheral = null;
        for (Peripheral p : peripherals) {
            if(p.getClass().getSimpleName().equals(peripheralType)){
                peripheral = p;
                break;
            }
        }
        if(peripherals.isEmpty() || peripheral == null) {
            throw new IllegalArgumentException(String.format(ExceptionMessages.NOT_EXISTING_PERIPHERAL, peripheralType, this.getClass().getSimpleName(), this.getId()));
        }
        return this.peripherals.remove(peripherals.indexOf(peripheral));
    }

    @Override
    public double getOverallPerformance() {
        if(components.isEmpty()){
            return super.getOverallPerformance();
        }
        double averageComponents = 0;
        for (Component component : components) {
            averageComponents += component.getOverallPerformance();
        }
        averageComponents /= components.size();
        return super.getOverallPerformance() + averageComponents;
    }

    @Override
    public double getPrice() {
        return this.peripherals.stream().mapToDouble(Product::getPrice).sum() + this.components.stream().mapToDouble(Product::getPrice).sum() + super.getPrice();
    }

    @Override
    public String toString() {
        StringBuilder componentsString = new StringBuilder();
        for(Component component : components) {
            componentsString.append("  ").append(component.toString()).append(System.lineSeparator());
        }

        StringBuilder peripheralsString = new StringBuilder();
        for (int i = 0; i < peripherals.size(); i++) {
            if(i == peripherals.size() - 1){
                peripheralsString.append("  ").append(peripherals.get(i).toString());
            }else {
                peripheralsString.append(System.lineSeparator()).append("  ").append(peripherals.get(i).toString()).append(System.lineSeparator());
            }
        }
        return String.format("Overall Performance: %.2f. Price: %.2f - %s: %s %s (Id: %d)%n" +
                " Components (%d):%n" +
                "%s" +
                " Peripherals (%d); Average Overall Performance (%.2f):%n" +
                "%s" ,
                this.getOverallPerformance(), this.getPrice(), this.getClass().getSimpleName(), super.getManufacturer(), super.getModel(), super.getId(),
                components.size(),componentsString.toString(), peripherals.size(),
                peripherals.stream().mapToDouble(Product::getOverallPerformance).average().orElse(0),
                peripheralsString.toString());
    }
}
