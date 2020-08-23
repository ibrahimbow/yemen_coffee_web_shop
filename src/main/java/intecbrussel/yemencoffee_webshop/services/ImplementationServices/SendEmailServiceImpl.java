package intecbrussel.yemencoffee_webshop.services.ImplementationServices;

import intecbrussel.yemencoffee_webshop.model.SendEmailInfo;
import intecbrussel.yemencoffee_webshop.repositories.SendEmailRepo;
import intecbrussel.yemencoffee_webshop.services.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendEmailServiceImpl implements SendEmailService {

    private SendEmailRepo sendEmailRepo;

    @Autowired
    public void setSendEmailRepo(SendEmailRepo sendEmailRepo) {
        this.sendEmailRepo = sendEmailRepo;
    }

    @Override
    public void SendEmailSendEmail(SendEmailInfo sendEmailInfo) {
        this.sendEmailRepo.sendEmail(sendEmailInfo);
    }
}
