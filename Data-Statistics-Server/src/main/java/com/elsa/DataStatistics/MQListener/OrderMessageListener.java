package com.elsa.DataStatistics.MQListener;

import com.elsa.DataStatistics.statistics.DayStatistics;
import com.elsa.DataStatistics.statistics.MonthStatistics;
import com.elsa.DataStatistics.statistics.YearStatistics;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderMessageListener {

    @Autowired
    private DayStatistics dayStatistics;

    @Autowired
    private MonthStatistics monthStatistics;

    @Autowired
    private YearStatistics yearStatistics;

    @RabbitListener(queues = "order_queue")
    public void receiveOrderQueue(String oId) {
        dayStatistics.dayStatistics(oId);
        monthStatistics.monthStatistics(oId);
        yearStatistics.yearStatistics(oId);
        System.out.println("已完成订单统计业务(RabbitMQ Direct order_queue)，id：" + oId);
    }

}
