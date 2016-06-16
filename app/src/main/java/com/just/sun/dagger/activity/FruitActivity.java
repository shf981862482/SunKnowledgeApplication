package com.just.sun.dagger.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.common.base.BaseFragmentActivity;
import com.just.sun.R;
import com.just.sun.dagger.bean.Fruit;
import com.just.sun.dagger.bean.ShoppingCarModel;
import com.just.sun.dagger.component.DaggerContainerComponent;
import com.just.sun.dagger.component.DaggerFruitComponent;
import com.just.sun.dagger.component.FruitComponent;
import com.just.sun.dagger.module.ContainerModule;
import com.just.sun.dagger.module.FruitModule;

import javax.inject.Inject;

public class FruitActivity extends BaseFragmentActivity {
    @Inject
    Fruit fruit;
    @Inject
    ShoppingCarModel shoppingCarModel;
    private TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);
//        DaggerFruitComponent.builder().build().inject(this);
        FruitComponent fruitComponent = DaggerFruitComponent.builder().fruitModule(new FruitModule()).build();

        DaggerContainerComponent.builder()
                .fruitComponent(fruitComponent).containerModule(new ContainerModule())
                .build().inject(this);
        txt = (TextView) findViewById(R.id.txt);
        txt.setText("dagger2：" + fruit.getName() + "---" + shoppingCarModel.getTotal());
    }
}
