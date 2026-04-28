package com.linkhub.linkhub.bootstrap.dev;

import com.linkhub.linkhub.content.application.dto.CreatePostCommand;
import com.linkhub.linkhub.content.application.usecase.CreatePostUseCase;
import com.linkhub.linkhub.modes.application.dto.SetModeCommand;
import com.linkhub.linkhub.modes.application.usecase.SetUserModeUseCase;
import com.linkhub.linkhub.users.application.dto.CreateUserCommand;
import com.linkhub.linkhub.users.application.usecase.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
@Order(2)
@RequiredArgsConstructor
public class DemoDataInitializer implements CommandLineRunner {
    private final CreateUserUseCase createUserUseCase;
    private final SetUserModeUseCase setUserModeUseCase;
    private final CreatePostUseCase createPostUseCase;

    @Override
    public void run(String... args) {
        Long userId = createDemoUser();

        setUserModeUseCase.setMode(new SetModeCommand(userId, "Calm"));

        createDemoPost(userId,
                """
                        📐 Теорема Фалеса: база, без которой в геометрии никуда
                        
                        Думаешь, геометрия — это только бесконечные вычисления? На самом деле, самые изящные задачи решаются на основе простых закономерностей. Одна из таких основ — теорема Фалеса.
                        Суть максимально проста: если параллельные прямые, пересекающие стороны угла, отсекают на одной его стороне равные между собой отрезки, то они отсекают равные между собой отрезки и на другой его стороне.
                        Почему это важно?
                        
                        Теорема Фалеса — это не просто абстракция, а мощный инструмент:
                            Пропорции: Она является фундаментом для понимания подобия треугольников.
                            Деление отрезка: С её помощью можно легко разделить отрезок на любое количество равных частей с помощью циркуля и линейки (вспоминаем школьную программу!).
                            Геометрические доказательства: Большинство задач на доказательство параллельности или равенства сторон строятся именно на этом принципе.
                            
                        Это классика, которая делает сложную геометрию чуточку понятнее. Используйте её, чтобы не считать лишнее там, где можно просто увидеть равенство! 💡
                        Планируешь ли ты использовать подобные геометрические принципы в своих задачах по олимпиадному программированию или в текущем проекте?""",
                "Learning");

        createDemoPost(userId,
                """
                        Косатки: океанские «суперкомпьютеры» 🐬
                        
                        Косатки — это не просто хищники, а настоящие интеллектуалы океана. Вот пара фактов, чтобы взглянуть на них по-новому:
                        
                            Это дельфины: Несмотря на пугающее название «кит-убийца», косатки — крупнейшие представители семейства дельфиновых.
                        
                            Сложный «софт»: У каждой стаи свои уникальные охотничьи стратегии и даже свои «языки» (диалекты), которые передаются от поколения к поколению.
                        
                            Матриархат: В стаях царит строгая иерархия под руководством опытных самок.
                        
                        Эти ребята — идеальный пример того, как высокая социализация и интеллект помогают доминировать в своей среде. Настоящие мастера командной работы! 🌊""",
                "Calm");

        createDemoPost(userId,
                """
                        Касатка: мастер воздушных виражей 🐦
                        
                        Понял, исправляюсь! Если с «а» — это уже не про океан, а про грациозную деревенскую ласточку. Совсем другой масштаб!
                        
                            Скоростные пилоты: Касатки — настоящие истребители в мире птиц. Они невероятно маневренны и ловят насекомых прямо на лету, развивая приличную скорость.
                        
                            Великие путешественники: Эти крохи совершают грандиозные перелеты, преодолевая тысячи километров из Европы в Африку и обратно.
                        
                            Символ перемен: В народной культуре прилет касаток — главный знак того, что зима окончательно отступила и пришла настоящая весна.
                        
                        Маленькая, быстрая и невероятно выносливая. Иногда самые обычные вещи вокруг нас — самые удивительные! ✨""",
                "Calm");

    }



    private Long createDemoUser() {
        return createUserUseCase.create(new CreateUserCommand("joy", "JOY")).id();
    }

    private void createDemoPost(Long userId, String text, String modeName) {
        createPostUseCase.create(new CreatePostCommand(
                userId,
                text,
                modeName
        ));
    }
}
