import com.tovelop.maphant.service.RedisService
import jakarta.servlet.http.HttpSessionEvent
import jakarta.servlet.http.HttpSessionListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SessionListener(@Autowired private val redisService: RedisService) : HttpSessionListener {

    override fun sessionDestroyed(se: HttpSessionEvent) {
        val sessionId = se.session.id

        // 해당 세션 ID에 대한 seed 값을 Redis에서 삭제.
        redisService.del(sessionId)
    }
}
