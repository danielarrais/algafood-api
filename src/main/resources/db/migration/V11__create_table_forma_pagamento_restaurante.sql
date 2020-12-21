create table forma_pagamento_restaurante
(
    restaurante_id integer
        constraint forma_pagamento_restaurante_restaurante_id_fk
            references restaurante,
    forma_pagamento_id int
        constraint forma_pagamento_restaurante_forma_pagamento_id_fk
            references forma_pagamento
);